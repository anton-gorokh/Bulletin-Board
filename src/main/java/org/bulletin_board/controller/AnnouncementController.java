package org.bulletin_board.controller;

import org.bulletin_board.dto.AnnouncementDto;
import org.bulletin_board.service.AnnouncementService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {
    private final AnnouncementService service;

    public AnnouncementController(AnnouncementService announcementService) {
        this.service = announcementService;
    }

    @GetMapping(path = "/{id}")
    public AnnouncementDto getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping(path = "/pagination/{page}/{size}")
    public List<AnnouncementDto> getPage(@PathVariable int page, @PathVariable int size) {
        return service.getPage(page, size);
    }

    @GetMapping(path = "/pagination/byCategory/{category}/{page}/{size}")
    public List<AnnouncementDto> getPageOfCategory(@PathVariable int page, @PathVariable int size, @PathVariable("category") String categoryName) {
        return service.getPageOfCategory(page, size, categoryName);
    }

    @PostMapping
    public long save(@RequestBody @Valid AnnouncementDto dto) throws Exception {
        return service.save(dto);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }

    @PutMapping(path = "/{id}")
    public void update(@RequestBody @Valid AnnouncementDto dto, @PathVariable Long id) {
        service.update(dto, id);
    }
}
