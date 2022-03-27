package org.bulletin_board.service;

import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.Rubric;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface AnnouncementService extends CrudService<Announcement> {
    List<Announcement> findByRubric(Rubric rubric);

    List<Announcement> findByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo);

    List<Announcement> findByPriceRange(BigDecimal priceFrom, BigDecimal priceTo);

    List<Announcement> findAllContainingWord(String word);

    void deleteInactiveAnnouncements();

    void deleteAllAnnouncementsByAuthorId(int authorId);
}
