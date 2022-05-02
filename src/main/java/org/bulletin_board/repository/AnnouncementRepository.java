package org.bulletin_board.repository;

import org.bulletin_board.domain.model.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AnnouncementRepository extends JpaRepository<Announcement, Long> {

    @Query("SELECT an FROM Announcement an JOIN an.category cat WHERE cat.name = :categoryName")
    Page<Announcement> findAllByCategoryName(Pageable pageable, @Param("categoryName") String categoryName);

    void deleteAllByAuthorId(Long id);

    void deleteAllByActiveFalse();
}
