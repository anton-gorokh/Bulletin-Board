package org.bulletin_board.service.mail;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.java.Log;
import org.bulletin_board.domain.model.Announcement;
import org.bulletin_board.domain.model.Author;
import org.bulletin_board.mail.FormatUtil;
import org.bulletin_board.mail.notice.Notice;
import org.bulletin_board.mail.notice.NoticeAttachment;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.text.DecimalFormat;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@Log
public class AnnouncementNoticeCreator extends Notice {
    private static final String TEMPLATE_NAME = "newAnnouncementNotification.ftlh";
    private static Template template;

    static {
        try {
            template = getFreemarkerConfiguration().getTemplate(TEMPLATE_NAME);
        } catch (IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public AnnouncementNoticeCreator(Announcement announcement, String subject, List<String> recipients, List<NoticeAttachment> noticeAttachments) {
        super(subject, recipients, prepareContent(announcement), noticeAttachments);
    }

    private static String prepareContent(Announcement announcement) {
        Writer content = new StringWriter();

        try {
            template.process(getReplaceMap(announcement), content);
        } catch (TemplateException | IOException e) {
            log.log(Level.SEVERE, e.getMessage(), e);
        }

        return content.toString();
    }

    private static Map<String, Object> getReplaceMap(Announcement announcement) {
        DecimalFormat decimalFormat = new DecimalFormat(FormatUtil.DECIMAL_SIMPLE_FORMAT);
        Author author = announcement.getAuthor();
        String updatedAt = DateTimeFormatter.ofPattern(FormatUtil.INSTANT_SIMPLE_FORMAT)
                .format(announcement.getUpdatedAt());
        String contactInfo = !author.getEmails().isEmpty() ? author.getEmails().get(0) : author.getPhones().get(0);
        String pay = decimalFormat.format(announcement.getPay());


        Map<String, Object> replaceMap = new HashMap<>();
        replaceMap.put("announcement", announcement);
        replaceMap.put("author", author);
        replaceMap.put("category", announcement.getCategory().getName());
        replaceMap.put("updatedAt", updatedAt);
        replaceMap.put("contactInfo", contactInfo);
        replaceMap.put("pay", pay);
        return replaceMap;
    }
}
