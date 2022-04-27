package org.bulletin_board.service;

import org.bulletin_board.domain.board.Announcement;
import org.bulletin_board.dto.AnnouncementDto;
import org.bulletin_board.repository.AnnouncementRepository;
import org.bulletin_board.service.author.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class AnnouncementService implements CrudService<AnnouncementDto> {
    private final AnnouncementRepository repository;
    private final AnnouncementMapper mapper;

    private final EmailService emailService;

    public AnnouncementService(AnnouncementRepository repository, AnnouncementMapper mapper, EmailService emailService) {
        this.repository = repository;
        this.mapper = mapper;
        this.emailService = emailService;
    }

    @Override
    public AnnouncementDto getById(Long id) {
        return mapper.mapToDto(repository.getById(id));
    }

    @Override
    public Long save(AnnouncementDto dto) {
        if (dto.getId() != null) {
            throw new IllegalArgumentException("Dto has id");
        }

        Long savedId = repository.save(mapper.mapToEntity(dto)).getId();
        emailService.sendEmails(repository.getById(savedId));
        return savedId;
    }

    @Override
    public void update(AnnouncementDto dto, Long id) {
        Announcement entity = mapper.mapToEntity(dto);
        entity.setId(id);
        repository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public void deleteAllByAuthorId(Long authorId) {
        repository.deleteAllByAuthorId(authorId);
    }

    @Scheduled(cron = "59 59 23 * * ?")
    public void deleteInactiveAnnouncements() {
        repository.deleteAllByActiveFalse();
    }
}
