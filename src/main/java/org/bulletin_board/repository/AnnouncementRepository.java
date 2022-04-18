package org.bulletin_board.repository;

import org.bulletin_board.domain.board.Announcement;
import org.bulletin_board.domain.board.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    List<Announcement> findByRubric(@NotNull(message = "Rubric cannot be null") @Valid Rubric rubric);

    List<Announcement> findAllByTextContaining(String word);

    @Transactional
    void deleteAllByAuthorId(Long id);

    @Transactional
    void deleteAllByActiveFalse();
}
