package service.impl;

import config.AppConfigTest;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.service.EmailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfigTest.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@WebAppConfiguration
@Sql(scripts = {
        "classpath:sql_scripts/announcement_truncate_table.sql",
        "classpath:sql_scripts/author_truncate_table.sql",
        "classpath:sql_scripts/rubric_truncate_table.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class EmailServiceImplTest {
    @Autowired
    @InjectMocks
    EmailService emailService;

    @Mock
    JavaMailSender jms;

    @Test
    public void shouldSendMail() {
        Mockito.doNothing().when(jms).send(Mockito.any(SimpleMailMessage.class));
    }
}
