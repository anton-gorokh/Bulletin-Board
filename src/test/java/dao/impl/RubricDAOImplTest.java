package dao.impl;

import config.AppConfigTest;
import domain.Util;
import org.bulletin_board.dao.RubricDAO;
import org.bulletin_board.domain.Rubric;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfigTest.class)
@WebAppConfiguration
@Sql(scripts = {
        "classpath:sql_scripts/rubric_truncate_table.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class RubricDAOImplTest {
    @Autowired
    RubricDAO rubricDAO;

    @Test
    public void shouldSaveRubric() {
        Rubric rubric = Util.getSampleRubric("sample");
        rubricDAO.save(rubric);

        Rubric savedRubric = rubricDAO.findById(1);
        Assertions.assertEquals(rubric.getName(), savedRubric.getName());
    }

    @Test
    public void shouldDeleteRubricById() {
        Rubric rubric = Util.getSampleRubric("sample");
        rubricDAO.save(rubric);

        rubricDAO.deleteById(1);
        Rubric savedRubric = rubricDAO.findById(1);

        Assertions.assertNull(savedRubric);
    }

    @Test
    public void shouldUpdateRubric() {
        Rubric rubric = Util.getSampleRubric("sample");
        rubricDAO.save(rubric);

        Rubric savedRubric = rubricDAO.findById(1);
        savedRubric.setName("changed name");
        rubricDAO.update(savedRubric);

        Rubric updatedRubric = rubricDAO.findById(1);
        Assertions.assertEquals("changed name", updatedRubric.getName());
    }

    @Test
    public void shouldFindAllRubrics() {
        Rubric first = Util.getSampleRubric("first");
        Rubric second = Util.getSampleRubric("second");

        rubricDAO.save(first);
        rubricDAO.save(second);
        List<Rubric> allSavedRubrics = rubricDAO.findAll();

        Assertions.assertEquals(2, allSavedRubrics.size());
    }

    @Test
    public void shouldFindRubricByName() {
        Rubric first = Util.getSampleRubric("first");
        Rubric second = Util.getSampleRubric("second");

        rubricDAO.save(first);
        rubricDAO.save(second);

        Rubric savedRubric = rubricDAO.findByName("first");
        Assertions.assertEquals("first", savedRubric.getName());
    }

    @Test
    public void shouldFindAllRubricsContainingWord() {
        Rubric first = Util.getSampleRubric("first rubric");
        Rubric second = Util.getSampleRubric("second rubric");
        Rubric third = Util.getSampleRubric("third");

        rubricDAO.save(first);
        rubricDAO.save(second);
        rubricDAO.save(third);

        List<Rubric> matchingRubrics = rubricDAO.findAllContainingWord("rubric");
        Assertions.assertEquals(2, matchingRubrics.size());
    }
}
