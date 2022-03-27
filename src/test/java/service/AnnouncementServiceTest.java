package service;

import config.AppConfigTest;
import domain.Util;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.Rubric;
import org.bulletin_board.domain.author.Author;
import org.bulletin_board.repository.AnnouncementRepository;
import org.bulletin_board.repository.AuthorRepository;
import org.bulletin_board.repository.RubricRepository;
import org.bulletin_board.service.AnnouncementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Set;

@SpringBootTest(classes = AppConfigTest.class)
@Sql(scripts =
        "classpath:sql_scripts/truncate_tablesH2.sql"
, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnnouncementServiceTest {
    // Test object
    @Autowired
    AnnouncementService announcementService;

    // Additional imports
    @Autowired
    AnnouncementRepository announcementRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    RubricRepository rubricRepository;

    // Saved in DB variables
    Rubric rubric;
    Author author;
    Announcement announcement;

    @BeforeEach
    public void setup() {
        this.author = Util.getSampleAuthor("Sample", "Au");
        author.setAddress(Util.getSampleAddress());
        author.setPhones(Set.of(Util.getSamplePhone()));
        author.setEmails(List.of(Util.getSampleEmail()));

        this.rubric = Util.getSampleRubric("Sample rubric");

        this.announcement = Util.getSampleAnnouncement("Sample An");
        announcement.setAuthor(author);
        announcement.setRubric(rubric);
        announcement.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit");
        announcement.setActive(true);
        announcement.setCreationDate(LocalDateTime.of(2000, Month.JANUARY, 2, 0, 0));
        announcement.setPay(new BigDecimal(2));

        rubricRepository.save(rubric);
        authorRepository.save(author);
        announcementRepository.save(announcement);
    }

    @Test
    public void shouldThrowNullPointerEx() {
        Assertions.assertThrows(NullPointerException.class, () -> {
            Announcement an = announcementService.findById(2);
        });
    }

    @Test
    public void shouldFindAnById() {
        Announcement an = announcementService.findById(1);
        Assertions.assertEquals(1, an.getId());
    }

    @Test
    public void shouldFindAnByRubric() {
        List<Announcement> anByRubric = announcementService.findByRubric(rubric);
        Assertions.assertAll(
                () -> Assertions.assertEquals(1, anByRubric.size()),
                () -> Assertions.assertEquals(1, anByRubric.get(0).getId())
                );
    }

    @Test
    public void shouldFindByDateRange() {
        List<Announcement> anByDateRange =
                announcementService.findByDateRange(
                        LocalDateTime.of(2000, Month.JANUARY, 1, 0, 0),
                        LocalDateTime.of(2000, Month.JANUARY, 3, 0, 0));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, anByDateRange.size()),
                () -> Assertions.assertEquals(1, anByDateRange.get(0).getId())
        );
    }

    @Test
    public void shouldFindByPriceRange() {
        List<Announcement> anByPriceRange =
                announcementService.findByPriceRange(new BigDecimal(1), new BigDecimal(2));

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, anByPriceRange.size()),
                () -> Assertions.assertEquals(1, anByPriceRange.get(0).getId())
        );
    }

    @Test
    public void shouldFindByWord() {
        List<Announcement> an = announcementService.findAllContainingWord("ipsum");

        Assertions.assertAll(
                () -> Assertions.assertEquals(1, an.get(0).getId())
        );
    }

    @Test
    public void shouldDeleteAllByAuthorId() {
        announcementService.deleteAllAnnouncementsByAuthorId(1);
        Assertions.assertThrows(NullPointerException.class, () -> {
            Announcement an = announcementService.findById(1);
        });
    }

    @Test
    public void shouldDeleteInactiveAnnouncement() {
        this.announcement.setActive(false);
        announcementRepository.save(announcement);

        announcementService.deleteInactiveAnnouncements();
        announcementService.deleteAllAnnouncementsByAuthorId(1);
        Assertions.assertThrows(NullPointerException.class, () -> {
            Announcement an = announcementService.findById(1);
        });
    }
}
