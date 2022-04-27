package org.bulletin_board.service;

import org.bulletin_board.domain.board.AnnouncementFilter;
import org.bulletin_board.dto.AnnouncementFilterDto;
import org.bulletin_board.repository.AnnouncementFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnnouncementFilterService {

    private final AnnouncementFilterRepository repository;
    private final AnnouncementFilterMapper mapper;

    @Autowired
    public AnnouncementFilterService(AnnouncementFilterRepository repository, AnnouncementFilterMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public AnnouncementFilterDto getById(Long id) {
        return mapper.mapToDto(repository.getById(id));
    }

    public long save(AnnouncementFilterDto dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Dto has id");
        }
        return repository.save(mapper.mapToEntity(dto)).getId();
    }

    public void update(AnnouncementFilterDto dto, Long id) {
        AnnouncementFilter entity = mapper.mapToEntity(dto);
        entity.setId(id);
        repository.save(entity);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
