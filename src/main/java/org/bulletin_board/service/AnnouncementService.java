package org.bulletin_board.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.board.Announcement;
import org.bulletin_board.domain.board.Category;
import org.bulletin_board.repository.AnnouncementRepository;
import org.bulletin_board.service.author.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Service
public class AnnouncementService {
    AnnouncementRepository announcementRepository;

    EmailService eService;

    @Autowired
    public AnnouncementService(AnnouncementRepository announcementRepository, EmailService eService) {
        this.announcementRepository = announcementRepository;
        this.eService = eService;
    }

    public void save(Announcement announcement) {
        announcementRepository.save(announcement);
        eService.sendEmails(announcement);
    }

    public Announcement findById(Long id) {
        return announcementRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("There is no announcement with such id. "));
    }

    public List<Announcement> findByRubric(Category category) {
        List<Announcement> byRubric = announcementRepository.findByCategoryId(category.getId());
        if (byRubric.isEmpty()) {
            throw new NullPointerException("There is no announcements with such Rubric. ");
        }

        return byRubric;
    }

    public List<Announcement> findAllContainingWord(String word) {
        List<Announcement> allByTextContaining = announcementRepository.findAllByTextContaining(word);
        if (allByTextContaining.isEmpty()) {
            throw new NullPointerException("There is no announcements containing such word. ");
        }

        return allByTextContaining;
    }

    public void update(Announcement announcement) {
        announcementRepository.save(announcement);
    }

    public void deleteById(Long id) {
        announcementRepository.deleteById(id);
    }

    public void deleteAllAnnouncementsByAuthorId(Long authorId) {
        announcementRepository.deleteAllByAuthorId(authorId);
    }

    @Scheduled(cron = "59 59 23 * * ?")
    public void deleteInactiveAnnouncements() {
        announcementRepository.deleteAllByActiveFalse();
    }
}
