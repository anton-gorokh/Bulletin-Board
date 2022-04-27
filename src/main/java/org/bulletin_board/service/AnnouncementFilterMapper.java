package org.bulletin_board.service;

import org.bulletin_board.domain.board.AnnouncementFilter;
import org.bulletin_board.dto.AnnouncementFilterDto;
import org.bulletin_board.repository.AuthorRepository;
import org.bulletin_board.repository.CategoryRepository;
import org.bulletin_board.service.author.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnnouncementFilterMapper {
    private final AuthorMapper authorMapper;
    private final CategoryRepository categoryRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public AnnouncementFilterMapper(AuthorMapper authorMapper, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    public AnnouncementFilterDto mapToDto(AnnouncementFilter entity) {
        return AnnouncementFilterDto.builder()
                .priceFrom(entity.getPriceFrom())
                .priceTo(entity.getPriceTo())
                .from(entity.getFrom())
                .to(entity.getTo())
                .title(entity.getTitle())
                .category(entity.getCategory().toSimpleValue())
                .author(authorMapper.mapToDto(entity.getAuthor()))
                .build();
    }

    public AnnouncementFilter mapToEntity(AnnouncementFilterDto dto) {
        return AnnouncementFilter.builder()
                .priceFrom(dto.getPriceFrom())
                .priceTo(dto.getPriceTo())
                .from(dto.getFrom())
                .to(dto.getTo())
                .title(dto.getTitle())
                .category(categoryRepository.getById(dto.getId()))
                .author(authorRepository.getById(dto.getAuthor().getId()))
                .build();
    }
}
