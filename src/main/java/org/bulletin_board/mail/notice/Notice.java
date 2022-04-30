package org.bulletin_board.mail.notice;

import freemarker.template.Configuration;
import lombok.Getter;
import org.bulletin_board.domain.model.mail.MailNotice;
import org.bulletin_board.domain.model.mail.MailNoticeAttachment;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public abstract class Notice {
    private final String subject;
    private final List<String> recipients;
    private final String content;
    private final List<NoticeAttachment> noticeAttachments;

    public Notice(String subject, List<String> recipients, String content, List<NoticeAttachment> noticeAttachments) {
        this.subject = subject;
        this.recipients = recipients;
        this.content = content;
        this.noticeAttachments = noticeAttachments;
    }

    protected static Configuration getFreemarkerConfiguration() throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_29);

        cfg.setDirectoryForTemplateLoading(new File("classpath:templates"));
        cfg.setDefaultEncoding("UTF-8");
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);

        return cfg;
    }

    public MailNotice toMailNotice() {
        Set<MailNoticeAttachment> attachments = this.getNoticeAttachments().stream()
                .map(this::toMailNoticeAttachment)
                .collect(Collectors.toSet());

        return MailNotice.builder()
                .subject(this.subject)
                .recipients(this.recipients)
                .content(this.content)
                .attachments(attachments)
                .build();
    }

    private MailNoticeAttachment toMailNoticeAttachment(NoticeAttachment attachment) {
        return MailNoticeAttachment.builder()
                .name(attachment.getOriginalFileName())
                .content(attachment.getContent())
                .build();
    }
}
