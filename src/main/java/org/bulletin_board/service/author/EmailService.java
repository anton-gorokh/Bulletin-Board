package org.bulletin_board.service.author;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.board.Announcement;
import org.bulletin_board.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class EmailService {
    EmailRepository emailRepository;

    JavaMailSender sender;

    @Autowired
    public EmailService(EmailRepository emailRepository, JavaMailSender sender) {
        this.emailRepository = emailRepository;
        this.sender = sender;
    }

    private List<String> findEmails(Announcement announcement) {
        return emailRepository.findEmails(
                announcement.getCategory(),
                announcement.getName(),
                announcement.getPay());
    }

    public void sendEmails(Announcement announcement) {
        // TODO
    }
}
