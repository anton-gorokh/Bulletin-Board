package org.bulletin_board.service;

import org.bulletin_board.domain.model.Announcement;
import org.bulletin_board.domain.model.mail.MailNotice;
import org.bulletin_board.domain.model.mail.MailNoticeAttachment;
import org.bulletin_board.mail.notice.Notice;
import org.bulletin_board.mail.notice.NoticeAttachment;
import org.bulletin_board.repository.MailNoticeAttachmentRepository;
import org.bulletin_board.repository.MailNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmailService {
    private final MailNoticeRepository mailNoticeRepository;
    private final MailNoticeAttachmentRepository mailNoticeAttachmentRepository;
    private final JavaMailSender sender;

    @Autowired
    public EmailService(MailNoticeRepository mailNoticeRepository, MailNoticeAttachmentRepository mailNoticeAttachmentRepository, JavaMailSender sender) {
        this.mailNoticeRepository = mailNoticeRepository;
        this.mailNoticeAttachmentRepository = mailNoticeAttachmentRepository;
        this.sender = sender;
    }

    private List<String> findEmails(Announcement announcement) {
        // TODO
        return null;
    }

    public void sendNotice(Notice notice) {
        MailNotice mailNotice = MailNotice.builder()
                .subject(notice.getSubject())
                .recipients(notice.getRecipients())
                .content(notice.getContent())
                .build();
        MailNotice savedMailNotice = mailNoticeRepository.save(mailNotice);

        List<MailNoticeAttachment> mailNoticeAttachments = notice.getNoticeAttachments().stream()
                .map(noticeAttachment -> mapToMailNoticeAttachment(noticeAttachment, savedMailNotice))
                .collect(Collectors.toList());
        mailNoticeAttachmentRepository.saveAll(mailNoticeAttachments);
    }

    private void sendEmails() {
        // TODO schedule cheking for not send emails & send
    }

    private MailNoticeAttachment mapToMailNoticeAttachment(NoticeAttachment noticeAttachment, MailNotice mailNotice) {
        return MailNoticeAttachment.builder()
                .name(noticeAttachment.getOriginalFileName())
                .content(noticeAttachment.getContent())
                .mailNotice(mailNotice)
                .build();
    }
}
