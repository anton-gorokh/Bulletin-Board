package org.bulletin_board.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.author.Author;
import org.bulletin_board.dto.AuthorDTO;
import org.bulletin_board.service.AuthorService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@RequestMapping("author")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthorController {
    final
    AuthorService authorService;

    @GetMapping("/find/{id}")
    public Author findById(@PathVariable("id") int id) {
        return authorService.findById(id);
    }


    @GetMapping("/find/dto/{id}")
    public AuthorDTO findDtoById(@PathVariable("id") int id) {
        Author author = authorService.findById(id);
        return new AuthorDTO(author);
    }

    @Secured("ROLE_USER")
    @PostMapping("/save")
    public void save(@RequestBody @Valid Author author) {
        authorService.save(author);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable int id) {
        authorService.deleteById(id);
    }

    @PutMapping("/update")
    public void updateMan(@RequestBody @Valid Author author) {
        authorService.update(author);
    }
}
