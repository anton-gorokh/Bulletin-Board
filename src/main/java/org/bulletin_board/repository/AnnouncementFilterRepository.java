package org.bulletin_board.repository;

import org.bulletin_board.domain.board.AnnouncementFilter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementFilterRepository extends JpaRepository<AnnouncementFilter, Long> {
}
