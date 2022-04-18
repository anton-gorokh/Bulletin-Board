package org.bulletin_board.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.TestWithLiquibase;
import org.bulletin_board.domain.board.author.Author;
import org.bulletin_board.dto.SimpleValue;
import org.bulletin_board.dto.author.AuthorDto;
import org.bulletin_board.repository.AuthorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@FieldDefaults(level = AccessLevel.PRIVATE)
@WithMockUser(roles = "USER")
public class AuthorControllerTest extends TestWithLiquibase {

    @Autowired
    WebApplicationContext webApplicationContext;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    MockMvc mvc;

    @Override
    protected String getChangeSetPath() {
        return "/authorController-changelog.xml";
    }

    @BeforeAll
    public void setup() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void shouldGetAuthor() throws Exception {
        System.out.println(passwordEncoder.encode("admin"));
        Author author = authorRepository.getById(1L);

        MvcResult result = mvc.perform(get("http://localhost:9999/author/" + author.getId()))
                .andExpect(status().isOk())
                .andReturn();

        AuthorDto authorDto = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });
        Assertions.assertAll(
                () -> Assertions.assertEquals(author.getFirstName(), authorDto.getFirstName()),
                () -> Assertions.assertEquals(author.getLastName(), authorDto.getLastName()),
                () -> Assertions.assertEquals(author.getAge(), authorDto.getAge()),
                () -> Assertions.assertEquals(author.getAddress().toString(), authorDto.getAddress().getName()),
                () -> Assertions.assertEquals(author.getPhones().stream()
                                .map(x -> new SimpleValue(x.getId(), x.getPhoneNumber()))
                                .collect(Collectors.toList()),
                        authorDto.getPhones()),
                () -> Assertions.assertEquals(author.getEmails().stream()
                                .map(x -> new SimpleValue(x.getId(), x.getName()))
                                .collect(Collectors.toList()),
                        authorDto.getEmails())
        );
    }
}
