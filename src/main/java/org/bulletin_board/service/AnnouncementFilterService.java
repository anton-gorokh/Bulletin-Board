package org.bulletin_board.service;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.repository.AnnouncementFilterRepository;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AnnouncementFilterService {

    AnnouncementFilterRepository repository;

    public org.bulletin_board.domain.board.AnnouncementFilter findById(Long id) {
        return repository.getById(id);
    }

    public void save(org.bulletin_board.domain.board.AnnouncementFilter announcementFilter) {
        repository.save(announcementFilter);
    }

    public void update(org.bulletin_board.domain.board.AnnouncementFilter announcementFilter) {
        repository.save(announcementFilter);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
