package org.bulletin_board.controller;

import org.bulletin_board.dto.AnnouncementFilterDto;
import org.bulletin_board.service.AnnouncementFilterService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/announcementFilter")
public class AnnouncementFilterController {
    private final AnnouncementFilterService service;

    @Autowired
    public AnnouncementFilterController(AnnouncementFilterService service) {
        this.service = service;
    }

    @GetMapping(path = "/byAuthor/{id}")
    public AnnouncementFilterDto getByAuthorId(@PathVariable Long authorId) {
        return service.getByAuthorId(authorId);
    }

    @PostMapping
    public long save(@RequestBody @Valid AnnouncementFilterDto dto) throws Exception {
        return service.save(dto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    public void update(@RequestBody @Valid AnnouncementFilterDto dto, @PathVariable Long id) {
        service.update(dto, id);
    }
}
