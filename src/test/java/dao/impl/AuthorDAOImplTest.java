package dao.impl;

import config.AppConfigTest;
import domain.Util;
import org.bulletin_board.dao.AuthorDAO;
import org.bulletin_board.domain.address.Address;
import org.bulletin_board.domain.author.Author;
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
        "classpath:sql_scripts/author_truncate_table.sql",
        "classpath:sql_scripts/address_truncate_table.sql"
}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
public class AuthorDAOImplTest {
    @Autowired
    AuthorDAO authorDAO;

    @Test
    public void shouldSaveAuthor() {
        Author author = Util.getSampleAuthor("John", "Johnson");
        author.setAddress(Util.getSampleAddress());
        authorDAO.save(author);

        Author savedAuthor = authorDAO.findById(1);
        Assertions.assertEquals(author.getFirstName(), savedAuthor.getFirstName());

    }

    @Test
    public void shouldDeleteAuthorById() {
        Author author = Util.getSampleAuthor("John", "Johnson");
        author.setAddress(Util.getSampleAddress());
        authorDAO.save(author);

        authorDAO.deleteById(1);
        Author savedAuthor = authorDAO.findById(1);

        Assertions.assertNull(savedAuthor);
    }

    @Test
    public void shouldUpdateAuthor() {
        Author author = Util.getSampleAuthor("John", "Johnson");
        author.setAddress(Util.getSampleAddress());
        authorDAO.save(author);

        Author savedAuthor = authorDAO.findById(1);
        savedAuthor.setFirstName("Robert");
        authorDAO.update(savedAuthor);

        Author updatedAuthor = authorDAO.findById(1);
        Assertions.assertEquals("Robert", updatedAuthor.getFirstName());
    }

    @Test
    public void shouldFindAllAuthors() {
        Author john = Util.getSampleAuthor("John", "Johnson");
        Author robert = Util.getSampleAuthor("Robert", "Robertson");

        Address johnAddress = Util.getSampleAddress();
        Address robertAddress = Util.getSampleAddress();
        john.setAddress(johnAddress);
        robert.setAddress(robertAddress);

        authorDAO.save(john);
        authorDAO.save(robert);
        List<Author> allSavedAuthors = authorDAO.findAll();

        Assertions.assertEquals(2, allSavedAuthors.size());
    }
}