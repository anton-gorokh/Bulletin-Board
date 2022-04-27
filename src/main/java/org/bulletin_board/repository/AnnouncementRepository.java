package org.bulletin_board.repository;

import org.bulletin_board.domain.board.Announcement;
import org.bulletin_board.domain.board.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findByCategoryId(Long categoryId);

    List<Announcement> findAllByTextContaining(String word);

    @Transactional
    void deleteAllByAuthorId(Long id);

    @Transactional
    void deleteAllByActiveFalse();
}
