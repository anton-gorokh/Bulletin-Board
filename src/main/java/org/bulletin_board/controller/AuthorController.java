package org.bulletin_board.controller;

import org.bulletin_board.dto.SimplePair;
import org.bulletin_board.dto.author.AuthorDto;
import org.bulletin_board.service.author.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping(path = "/{id}")
    public AuthorDto getById(@PathVariable Long id) {
        return authorService.getById(id);
    }

    @GetMapping(path = "/pagination/{page}/{size}")
    public List<AuthorDto> getPage(@PathVariable int page, @PathVariable int size) {
        return authorService.getPage(page, size);
    }

    @PostMapping
    public long save(@RequestBody @Valid AuthorDto author) throws Exception {
        return authorService.save(author);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable Long id) {
        authorService.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    public void update(@RequestBody @Valid AuthorDto author, @PathVariable Long id) {
        authorService.update(author, id);
    }

    @PutMapping(path = "/{id}/changePassword")
    public void updatePassword(@RequestBody SimplePair oldNewPassword, @PathVariable Long id) {
        authorService.updatePassword(oldNewPassword, id);
    }
}
