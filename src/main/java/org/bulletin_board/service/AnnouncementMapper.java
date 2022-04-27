package org.bulletin_board.service;

import org.bulletin_board.domain.board.Announcement;
import org.bulletin_board.dto.AnnouncementDto;
import org.bulletin_board.repository.AuthorRepository;
import org.bulletin_board.repository.CategoryRepository;
import org.bulletin_board.service.author.AuthorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnnouncementMapper implements SimpleDtoMapper<Announcement, AnnouncementDto> {
    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public AnnouncementMapper(AuthorMapper authorMapper, AuthorRepository authorRepository, CategoryRepository categoryRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public AnnouncementDto mapToDto(Announcement entity) {
        return AnnouncementDto.builder()
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .name(entity.getName())
                .text(entity.getText())
                .pay(entity.getPay())
                .active(entity.getActive())
                .author(authorMapper.mapToDto(entity.getAuthor()))
                .category(entity.getCategory().toSimpleValue())
                .build();
    }

    public Announcement mapToEntity(AnnouncementDto dto) {
        return Announcement.builder()
                .name(dto.getName())
                .text(dto.getText())
                .pay(dto.getPay())
                .active(dto.getActive())
                .author(authorRepository.getById(dto.getAuthor().getId()))
                .category(categoryRepository.getById(dto.getCategory().getId()))
                .build();
    }
}
