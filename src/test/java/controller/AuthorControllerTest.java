package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import config.AppConfigTest;
import domain.Util;
import org.bulletin_board.controller.AuthorController;
import org.bulletin_board.domain.author.Author;
import org.bulletin_board.service.AuthorService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthorController.class)
@ContextConfiguration(classes = AppConfigTest.class)
@WebAppConfiguration
public class AuthorControllerTest {
    public static final ObjectMapper MAPPER = new ObjectMapper();

    @MockBean
    AuthorService authorService;

    @Autowired
    MockMvc mockMvc;

    @Test
    @WithMockUser(username = "John", roles = {"USER"})
    public void shouldSaveAuthorObject() throws Exception {
        Mockito.doNothing().when(authorService).save(ArgumentMatchers.any(Author.class));

        Author author = Util.getSampleAuthor("John", "Johnson");
        String jsonAuthor = MAPPER.writeValueAsString(author);

        mockMvc.perform(post("/author/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAuthor))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSaveAuthorObjectWithException() throws Exception {
        Mockito.doNothing().when(authorService).save(ArgumentMatchers.any(Author.class));

        Author author = Util.getSampleAuthor("John", "Johnson");
        author.setAge(15);

        String json = MAPPER.writeValueAsString(author);
        mockMvc.perform(post("/author/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().is(409));
    }

    @Test
    public void shouldGetAuthorObject() throws Exception {

        Author author = Util.getSampleAuthor("John", "Johnson");
        author.setId(13);

        Mockito.when(authorService.findById(ArgumentMatchers.anyInt())).thenReturn(author);

        mockMvc.perform(get("/author/find/{id}", 2))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").value(13));
    }
}
