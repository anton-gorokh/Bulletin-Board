package org.bulletin_board.controller;

import org.bulletin_board.dto.SimpleValue;
import org.bulletin_board.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;

    @Autowired
    public CategoryController(CategoryService service) {
        this.service = service;
    }

    @GetMapping
    public List<SimpleValue> getValues() {
        return service.getAll();
    }

    @PostMapping
    public Long save(@RequestBody SimpleValue dto) {
        return service.save(dto);
    }
}
