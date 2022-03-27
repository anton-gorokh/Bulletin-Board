package org.bulletin_board.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.Rubric;
import org.bulletin_board.repository.AnnouncementRepository;
import org.bulletin_board.service.AnnouncementService;
import org.bulletin_board.service.EmailService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@AllArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {
    final
    AnnouncementRepository announcementRepository;

    final
    EmailService eService;

    @Override
    public void save(Announcement announcement) {
        announcementRepository.save(announcement);
        eService.sendEmails(announcement);
    }

    @Override
    public Announcement findById(int id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("There is no announcement with such id. "));
    }

    @Override
    public List<Announcement> findByRubric(Rubric rubric) {
        List<Announcement> byRubric = announcementRepository.findByRubric(rubric);
        if (byRubric.isEmpty()) {
            throw new NullPointerException("There is no announcements with such Rubric. ");
        }

        return byRubric;
    }

    @Override
    public List<Announcement> findByDateRange(LocalDateTime dateFrom, LocalDateTime dateTo) {
        List<Announcement> byDateRange = announcementRepository.findByDateRange(dateFrom, dateTo);
        if (byDateRange.isEmpty()) {
            throw new NullPointerException("There is no announcements created in this date range. ");
        }

        return byDateRange;
    }

    @Override
    public List<Announcement> findByPriceRange(BigDecimal priceFrom, BigDecimal priceTo) {
        List<Announcement> byPriceRange = announcementRepository.findByPriceRange(priceFrom, priceTo);
        if (byPriceRange.isEmpty()) {
            throw new NullPointerException("There is no announcements with such price. ");
        }

        return byPriceRange;
    }

    @Override
    public List<Announcement> findAllContainingWord(String word) {
        List<Announcement> allByTextContaining = announcementRepository.findAllByTextContaining(word);
        if (allByTextContaining.isEmpty()) {
            throw new NullPointerException("There is no announcements containing such word. ");
        }

        return allByTextContaining;
    }

    @Override
    public void update(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    @Override
    public void deleteById(int id) {
        announcementRepository.deleteById(id);
    }

    @Override
    public void deleteAllAnnouncementsByAuthorId(int authorId) {
        announcementRepository.deleteAllByAuthorId(authorId);
    }

    @Scheduled(cron = "59 59 23 * * ?")
    @Override
    public void deleteInactiveAnnouncements() {
        announcementRepository.deleteAllByActiveFalse();
    }
}
