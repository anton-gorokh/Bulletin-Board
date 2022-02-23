package dao.impl;

import config.AppConfigTest;
import domain.Util;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.*;
import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.MatchingAd;
import org.bulletin_board.domain.Rubric;
import org.bulletin_board.domain.author.Author;
import org.bulletin_board.domain.author.Email;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.math.BigDecimal;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfigTest.class)
@WebAppConfiguration
@FieldDefaults(level = AccessLevel.PRIVATE)
@Sql(scripts = {
        "classpath:sql_scripts/mad_truncate_table.sql",
        "classpath:sql_scripts/author_truncate_table.sql",
        "classpath:sql_scripts/rubric_truncate_table.sql",
        "classpath:sql_scripts/address_truncate_table.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class EmailDAOImplTest {
    @Autowired
    EmailDAO emailDAO;

    @Autowired
    AnnouncementDAO announcementDAO;

    @Autowired
    AuthorDAO authorDAO;

    @Autowired
    RubricDAO rubricDAO;

    @Autowired
    CrudDAO<MatchingAd> madDAO;

    Announcement announcement;

    Author author;

    Rubric rubric;

    @BeforeEach
    public void setup() {
        author = Util.getSampleAuthor("John", "Johnson");
        author.setAddress(Util.getSampleAddress());

        Email email = Util.getSampleEmail();
        email.setAuthor(author);
        author.setEmails(List.of(email));

        announcement = Util.getSampleAnnouncement("Sample announcement");
        announcement.setPay(new BigDecimal(15));
        announcement.setAuthor(author);

        rubric = Util.getSampleRubric("Sample rubric");
        announcement.setRubric(rubric);

        MatchingAd mad = Util.getSampleMad("Sample mad", new BigDecimal(0), new BigDecimal(20));
        mad.setAuthor(author);
        mad.setRubric(rubric);

        authorDAO.save(author);
        rubricDAO.save(rubric);
        announcementDAO.save(announcement);
        madDAO.save(mad);
    }

    @Test
    public void shouldFindEmails() {
        List<String> emails = emailDAO.findEmails(announcement);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, emails.size()),
                () -> Assertions.assertEquals("sample@mail.com", emails.get(0)));
    }
}
