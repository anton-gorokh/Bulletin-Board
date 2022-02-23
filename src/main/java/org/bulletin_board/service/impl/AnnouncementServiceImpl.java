package org.bulletin_board.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.AnnouncementDAO;
import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.Rubric;
import org.bulletin_board.service.AnnouncementService;
import org.bulletin_board.service.EmailService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@AllArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    final
    AnnouncementDAO announcementDAO;

    final
    EmailService eService;

    @Override
    public Announcement findById(int id) {
        return announcementDAO.findById(id);
    }

    @Override
    public void save(Announcement announcement) {
        announcementDAO.save(announcement);
        eService.sendEmails(announcement);
    }

    @Override
    public void update(Announcement announcement) {
        announcementDAO.update(announcement);
    }

    @Override
    public void deleteById(int id) {
        announcementDAO.deleteById(id);
    }

    @Override
    public List<Announcement> findByRubrics(List<Rubric> rubrics) {
        return announcementDAO.findByRubrics(rubrics);
    }

    @Override
    public List<Announcement> findBetweenTwoDates(Date from, Date to) {
        return announcementDAO.findBetweenTwoDates(from, to);
    }

    @Override
    public List<Announcement> findByPriceRange(BigDecimal priceFrom, BigDecimal priceTo) {
        return announcementDAO.findByPriceRange(priceFrom, priceTo);
    }

    @Override
    public List<Announcement> findAllContainingWord(String word) {
        return announcementDAO.findAllContainingWord(word);
    }
}
