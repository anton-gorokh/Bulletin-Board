package org.bulletin_board.mail.notice;

import lombok.Getter;
import java.util.List;

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
}
