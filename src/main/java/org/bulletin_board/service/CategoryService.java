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
public class CategoryService implements CrudService<SimpleValue> {
    private final CategoryRepository repository;

    @Autowired
    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public List<SimpleValue> getAll() {
        return MapperUtil.toSimpleValues(repository.findAll());
    }

    @Override
    public SimpleValue getById(Long id) {
        return repository.getById(id).toSimpleValue();
    }

    @Override
    public Long save(SimpleValue dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Dto has id");
        }
        return repository.save(Category.builder().name(dto.getName()).build()).getId();
    }

    @Override
    public void update(SimpleValue dto, Long id) {
        Category entity = Category.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
        repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
