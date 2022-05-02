package org.bulletin_board.service;

import liquibase.pro.packaged.A;
import org.bulletin_board.domain.model.Announcement;
import org.bulletin_board.domain.model.Announcement_;
import org.bulletin_board.domain.model.Author;
import org.bulletin_board.dto.AnnouncementDto;
import org.bulletin_board.dto.author.AuthorDto;
import org.bulletin_board.mail.MailNoticeSender;
import org.bulletin_board.repository.AnnouncementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class AnnouncementService implements CrudService<AnnouncementDto> {

    private final AnnouncementFilterService announcementFilterService;
    private final AnnouncementRepository repository;
    private final AnnouncementMapper mapper;
    private final MailNoticeSender mailNoticeSender;

    @Autowired
    public AnnouncementService(AnnouncementFilterService announcementFilterService, AnnouncementRepository repository, AnnouncementMapper mapper, MailNoticeSender mailNoticeSender) {
        this.announcementFilterService = announcementFilterService;
        this.repository = repository;
        this.mapper = mapper;
        this.mailNoticeSender = mailNoticeSender;
    }

    public List<AnnouncementDto> getPage(int page, int size) {
        PageRequest request = PageRequest.of(page, size, Sort.by(Announcement_.UPDATED_AT).descending());
        List<Announcement> authors = repository.findAll(request).getContent();
        return authors.stream().map(mapper::mapToDto).collect(Collectors.toList());
    }

    public List<AnnouncementDto> getPageOfCategory(int page, int size, String categoryName) {
        PageRequest request = PageRequest.of(page, size, Sort.by(Announcement_.UPDATED_AT).descending());
        List<Announcement> authors = repository.findAllByCategoryName(request, categoryName).getContent();
        return authors.stream().map(mapper::mapToDto).collect(Collectors.toList());
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

        Announcement entity = repository.save(mapper.mapToEntity(dto));
        announcementFilterService.findMatchingAndSendNotice(entity);
        return entity.getId();
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
