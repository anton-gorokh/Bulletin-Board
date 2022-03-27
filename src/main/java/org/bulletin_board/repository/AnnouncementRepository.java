package org.bulletin_board.repository;

import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

    List<Announcement> findByRubric(@NotNull(message = "Rubric cannot be null") @Valid Rubric rubric);

    @Transactional
    @Query("SELECT an FROM Announcement an WHERE an.creationDate BETWEEN :dateFrom AND :dateTo")
    List<Announcement> findByDateRange(@Param("dateFrom") LocalDateTime dateFrom,
                                       @Param("dateTo") LocalDateTime dateTo);

    @Query("SELECT an FROM Announcement an WHERE an.pay >= :priceFrom AND an.pay <= :priceTo")
    List<Announcement> findByPriceRange(@Param("priceFrom") BigDecimal priceFrom,
                                        @Param("priceTo") BigDecimal priceTo);

    List<Announcement> findAllByTextContaining(String word);

    @Transactional
    void deleteAllByAuthorId(int id);

    @Transactional
    void deleteAllByActiveFalse();
}
