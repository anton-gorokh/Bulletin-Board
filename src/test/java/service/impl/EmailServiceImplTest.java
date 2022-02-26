package service.impl;

import config.AppConfigTest;
import domain.Util;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.EmailDAO;
import org.bulletin_board.domain.Announcement;
import org.bulletin_board.service.EmailService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ContextConfiguration(classes = AppConfigTest.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@WebAppConfiguration
@Sql(scripts = {
        "classpath:sql_scripts/announcement_truncate_table.sql",
        "classpath:sql_scripts/author_truncate_table.sql",
        "classpath:sql_scripts/rubric_truncate_table.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class EmailServiceImplTest {
    @Mock
    EmailDAO emailDAO;

    @Mock
    JavaMailSender jms;

    @Autowired
    @InjectMocks
    EmailService emailService;

    @BeforeAll
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldSendMail() {
        // Mock mail sending
        Mockito.doNothing().when(jms).send(Mockito.any(SimpleMailMessage.class));
        // Mock dao layer
        Mockito.doReturn(List.of("sample@mail.com"))
                .when(emailDAO).findEmails(Mockito.any(Announcement.class));

        Announcement announcement = Util.getSampleAnnouncement("Sample announcement");
        emailService.sendEmails(announcement);

        // Verify that jms.send() method was called
        Mockito.verify(jms, Mockito.times(1)).send(Mockito.any(SimpleMailMessage.class));
    }
}
