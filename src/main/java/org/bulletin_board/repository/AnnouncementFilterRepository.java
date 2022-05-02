package org.bulletin_board.repository;

import org.bulletin_board.domain.model.AnnouncementFilter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementFilterRepository extends JpaRepository<AnnouncementFilter, Long> {
    void deleteAllByAuthorId(Long id);

    AnnouncementFilter getByAuthorId(Long authorId);
}
