package org.bulletin_board.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.Announcement;
import org.bulletin_board.repository.EmailRepository;
import org.bulletin_board.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@AllArgsConstructor
public class EmailServiceImpl implements EmailService {
    @Autowired
    EmailRepository emailRepository;

    @Autowired
    JavaMailSender sender;

    private List<String> findEmails(Announcement announcement) {
        return emailRepository.findEmails(
                announcement.getAuthor(),
                announcement.getRubric(),
                announcement.getName(),
                announcement.getPay());
    }

    @Override
    public void sendEmails(Announcement announcement) {
        List<String> emails = findEmails(announcement);
        String[] array = emails.toArray(String[]::new);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(array);
        message.setSubject(String.format(
                "New announcement: %s", announcement.getName()));
        message.setText(String.format(
                "A new announcement matching your filet has been added!\n\n" +
                        "Rubric: %s\n" +
                        "Title: %s\n" +
                        "Author: %s, %s\n" +
                        "Pay: %s\n\n" +
                        "Text: %s",
                announcement.getRubric(),
                announcement.getName(),
                announcement.getAuthor(),
                announcement.getCreationDate(),
                announcement.getPay(),
                announcement.getText()));

        sender.send(message);
    }
}
