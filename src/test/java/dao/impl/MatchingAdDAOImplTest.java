package dao.impl;

import config.AppConfigTest;
import domain.Util;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.AuthorDAO;
import org.bulletin_board.dao.CrudDAO;
import org.bulletin_board.dao.RubricDAO;
import org.bulletin_board.domain.MatchingAd;
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
public class MatchingAdDAOImplTest {
    @Autowired
    CrudDAO<MatchingAd> matchingAdDAO;

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
    public void shouldSaveMad() {
        MatchingAd mad = Util.getSampleMad("Sample mad", new BigDecimal(0), new BigDecimal(1));
        mad.setAuthor(author);
        mad.setRubric(rubric);
        matchingAdDAO.save(mad);

        MatchingAd savedMad = matchingAdDAO.findById(1);
        Assertions.assertEquals(mad.getTitle(), savedMad.getTitle());
    }

    @Test
    public void shouldDeleteMadById() {
        MatchingAd mad = Util.getSampleMad("Sample mad", new BigDecimal(0), new BigDecimal(1));
        mad.setAuthor(author);
        mad.setRubric(rubric);
        matchingAdDAO.save(mad);

        matchingAdDAO.deleteById(1);
        MatchingAd savedMad = matchingAdDAO.findById(1);
        Assertions.assertNull(savedMad);
    }

    @Test
    public void shouldUpdateMad() {
        MatchingAd mad = Util.getSampleMad("Sample mad", new BigDecimal(0), new BigDecimal(1));
        mad.setAuthor(author);
        mad.setRubric(rubric);
        matchingAdDAO.save(mad);

        MatchingAd savedMad = matchingAdDAO.findById(1);
        savedMad.setTitle("New sample title");
        matchingAdDAO.update(savedMad);

        MatchingAd updatedMad = matchingAdDAO.findById(1);
        Assertions.assertEquals("New sample title", updatedMad.getTitle());
    }
}
