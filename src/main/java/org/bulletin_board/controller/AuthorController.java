package org.bulletin_board.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dto.author.AuthorDto;
import org.bulletin_board.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/author")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorController {
    AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Secured("ROLE_USER")
    @GetMapping("/{id}")
    public AuthorDto find(@PathVariable("id") Long id) {
        return authorService.findById(id);
    }

    @Secured("ROLE_USER")
    @PostMapping("/")
    public long save(@RequestBody @Valid AuthorDto author) throws Exception {
        return authorService.save(author);
    }


    @Secured("ROLE_USER")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    @Secured("ROLE_USER")
    @PutMapping("/")
    public void update(@RequestBody @Valid AuthorDto author) {
        authorService.update(author);
    }
}
