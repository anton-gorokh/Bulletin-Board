package org.bulletin_board.service;


import org.bulletin_board.domain.board.Category;
import org.bulletin_board.dto.SimpleValue;
import org.bulletin_board.repository.CategoryRepository;
import org.bulletin_board.service.util.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CategoryService {
    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public SimpleValue getById(Long id) {
        return repository.getById(id).toSimpleValue();
    }

    public List<SimpleValue> getAll() {
        return MapperUtil.toSimpleValues(repository.findAll());
    }

    public void save(SimpleValue dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Dto has id");
        }
        repository.save(Category.builder().name(dto.getName()).build());
    }

    public void update(SimpleValue dto, Long id) {
        Category entity = Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
        repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
