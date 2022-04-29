package org.bulletin_board.mail;

import lombok.extern.java.Log;
import org.bulletin_board.domain.model.mail.MailNotice;
import org.bulletin_board.repository.MailNoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;
import java.util.logging.Level;

@Log
@Service
public class MailNoticeSenderScheduler {
    private final MailNoticeRepository mailNoticeRepository;
    private final MailNoticeSender mailNoticeSender;

    @Autowired
    public MailNoticeSenderScheduler(MailNoticeRepository mailNoticeRepository, MailNoticeSender mailNoticeSender) {
        this.mailNoticeRepository = mailNoticeRepository;
        this.mailNoticeSender = mailNoticeSender;
    }

    @Scheduled(fixedRateString = "${application.sender.mail.schedule-fixed-rate-string}")
    public void execute() {
        List<MailNotice> notSendMailNotices = mailNoticeRepository.findBySentAtIsNull();
        log.fine("Mail notices to send: " + notSendMailNotices.size());

        for (MailNotice notice : notSendMailNotices) {
            try {
                mailNoticeSender.sendMailNotice(notice);
            } catch (MessagingException e) {
                log.log(Level.SEVERE, e.getMessage(), e);
            }
        }
    }
}
