package dao.impl;

import config.AppConfigTest;
import domain.Util;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.AnnouncementDAO;
import org.bulletin_board.dao.AuthorDAO;
import org.bulletin_board.dao.RubricDAO;
import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.Rubric;
import org.bulletin_board.domain.author.Author;
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
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfigTest.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
@WebAppConfiguration
@Sql(scripts = {
        "classpath:sql_scripts/announcement_truncate_table.sql",
        "classpath:sql_scripts/author_truncate_table.sql",
        "classpath:sql_scripts/rubric_truncate_table.sql",
        "classpath:sql_scripts/address_truncate_table.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AnnouncementDAOImplTest {
    @Autowired
    AnnouncementDAO announcementDAO;

    @Autowired
    AuthorDAO authorDAO;

    @Autowired
    RubricDAO rubricDAO;

    Author author;

    Rubric rubric;

    @BeforeEach
    public void setup() {
        author = Util.getSampleAuthor("John", "Johnson");
        author.setAddress(Util.getSampleAddress());
        rubric = Util.getSampleRubric("Sample rubric");

        authorDAO.save(author);
        rubricDAO.save(rubric);
    }

    @Test
    public void shouldSaveAnnouncement() {
        Announcement announcement = Util.getSampleAnnouncement("Sample announcement");

        announcement.setAuthor(author);
        announcement.setRubric(rubric);
        announcementDAO.save(announcement);

        Announcement savedAnnouncement = announcementDAO.findById(1);
        Assertions.assertEquals(announcement.getName(), savedAnnouncement.getName());
    }

    @Test
    public void shouldUpdateAnnouncement() {
        Announcement announcement = Util.getSampleAnnouncement("Sample announcement");

        announcement.setAuthor(author);
        announcement.setRubric(rubric);
        announcementDAO.save(announcement);

        Announcement savedAnnouncement = announcementDAO.findById(1);
        savedAnnouncement.setName("New sample announcement");
        announcementDAO.update(savedAnnouncement);

        Announcement updatedAnnouncement = announcementDAO.findById(1);
        Assertions.assertEquals("New sample announcement", updatedAnnouncement.getName());
    }

    @Test
    public void shouldDeleteAnnouncement() {
        Announcement announcement = Util.getSampleAnnouncement("Sample announcement");

        announcement.setAuthor(author);
        announcement.setRubric(rubric);

        announcementDAO.save(announcement);
        announcementDAO.deleteById(1);
        Announcement savedAnnouncement = announcementDAO.findById(1);

        Assertions.assertNull(savedAnnouncement);
    }

    @Test
    public void shouldFindAnnouncementsByAuthor() {
        Announcement first = Util.getSampleAnnouncement("Sample announcement");
        Announcement second = Util.getSampleAnnouncement("Sample announcement");

        first.setAuthor(author);
        second.setAuthor(author);
        first.setRubric(rubric);
        second.setRubric(rubric);

        announcementDAO.save(first);
        announcementDAO.save(second);

        List<Announcement> savedAnnouncements = announcementDAO.findByAuthor(1);
        Assertions.assertEquals(2, savedAnnouncements.size());
    }

    @Test
    public void shouldFindAnnouncementsByRubrics() {
        Announcement first = Util.getSampleAnnouncement("Sample announcement");
        Announcement second = Util.getSampleAnnouncement("Sample announcement");

        first.setAuthor(author);
        second.setAuthor(author);
        first.setRubric(rubric);
        second.setRubric(rubric);

        announcementDAO.save(first);
        announcementDAO.save(second);

        List<Announcement> savedAnnouncements = announcementDAO.findByRubrics(List.of(rubric));
        Assertions.assertEquals(2, savedAnnouncements.size());
    }

    @Test
    public void shouldFindBetweenTwoDates() {
        Announcement first = Util.getSampleAnnouncement("First sample announcement");
        first.setCreationDate(new Date(2000, Calendar.JANUARY, 1));
        Announcement second = Util.getSampleAnnouncement("Second sample announcement");
        second.setCreationDate(new Date(2005, Calendar.JANUARY, 1));

        first.setAuthor(author);
        second.setAuthor(author);
        first.setRubric(rubric);
        second.setRubric(rubric);

        announcementDAO.save(first);
        announcementDAO.save(second);
        List<Announcement> ansBetweenTwoDates = announcementDAO.findBetweenTwoDates(
                new Date(1999, Calendar.JANUARY, 1),
                new Date(2001, Calendar.JANUARY, 1));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, ansBetweenTwoDates.size()),
                () -> Assertions.assertEquals("First sample announcement", ansBetweenTwoDates.get(0).getName()));
    }

    @Test
    public void shouldFindByPrice() {
        Announcement first = Util.getSampleAnnouncement("First sample announcement");
        first.setPay(new BigDecimal(15));
        Announcement second = Util.getSampleAnnouncement("Second sample announcement");
        second.setPay(new BigDecimal(25));

        first.setAuthor(author);
        second.setAuthor(author);
        first.setRubric(rubric);
        second.setRubric(rubric);

        announcementDAO.save(first);
        announcementDAO.save(second);
        List<Announcement> ansByPriceRange =
                announcementDAO.findByPriceRange(new BigDecimal(0), new BigDecimal(20));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, ansByPriceRange.size()),
                () -> Assertions.assertEquals(
                        first.getPay().doubleValue(),
                        ansByPriceRange.get(0).getPay().doubleValue()));
    }

    @Test
    public void shouldFindAllContainingWord() {
        Announcement first = Util.getSampleAnnouncement("First sample announcement");
        Announcement second = Util.getSampleAnnouncement("Second sample announcement");

        first.setAuthor(author);
        second.setAuthor(author);
        first.setRubric(rubric);
        second.setRubric(rubric);

        announcementDAO.save(first);
        announcementDAO.save(second);
        List<Announcement> ansByWord =
                announcementDAO.findAllContainingWord("First");

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, ansByWord.size()),
                () -> Assertions.assertEquals(first.getName(), ansByWord.get(0).getName()));
    }
}
