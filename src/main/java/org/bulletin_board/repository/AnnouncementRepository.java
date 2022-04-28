package org.bulletin_board.repository;

import org.bulletin_board.domain.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    void deleteAllByAuthorId(Long id);

    void deleteAllByActiveFalse();
}
