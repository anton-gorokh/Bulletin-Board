package org.bulletin_board.mail;

import org.bulletin_board.domain.model.mail.MailNotice;
import org.bulletin_board.domain.model.mail.MailNoticeAttachment;
import org.bulletin_board.repository.MailNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@Service
public class MailNoticeSender {
    private final static String ENCODING = "UTF-8";

    @Value("${application.sender.from}")
    private String from;

    private final JavaMailSender javaMailSender;
    private final MailNoticeRepository mailNoticeRepository;

    @Autowired
    public MailNoticeSender(JavaMailSender javaMailSender, MailNoticeRepository mailNoticeRepository) {
        this.javaMailSender = javaMailSender;
        this.mailNoticeRepository = mailNoticeRepository;
    }

    @Transactional
    public void sendMailNotice(MailNotice notice) throws MessagingException {
        List<String> recipients = notice.getRecipients();

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, ENCODING);
        messageHelper.setBcc(recipients.toArray(new String[recipients.size()]));
        messageHelper.setSubject(notice.getSubject());
        messageHelper.setText(notice.getContent(), true);
        if (from != null) {
            messageHelper.setFrom(from);
        }

        for (MailNoticeAttachment attachment : notice.getAttachments()) {
            ByteArrayResource resource = new ByteArrayResource(attachment.getContent().getBytes());
            messageHelper.addAttachment(attachment.getName(), resource);
        }

        javaMailSender.send(message);
        notice.setSentAt(Instant.now());
        mailNoticeRepository.save(notice);
    }
}
