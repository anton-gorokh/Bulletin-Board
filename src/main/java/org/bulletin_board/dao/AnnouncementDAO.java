package org.bulletin_board.dao;

import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.Rubric;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AnnouncementDAO extends CrudDAO<Announcement> {
    List<Announcement> findAllContainingWord(String word);

    List<Announcement> findByAuthor(int authorId);

    List<Announcement> findByRubrics(List<Rubric> rubrics);

    List<Announcement> findBetweenTwoDates(Date from, Date to);

    List<Announcement> findByPriceRange(BigDecimal priceFrom, BigDecimal priceTo);
}
