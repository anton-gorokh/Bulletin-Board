package org.bulletin_board.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.AnnouncementDAO;
import org.bulletin_board.dao.AuthorDAO;
import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.author.Author;
import org.bulletin_board.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    final
    AuthorDAO authorDAO;

    final
    AnnouncementDAO announcementDAO;

    private void deleteAllAnnouncements(int authorId) {
        List<Announcement> byAuthor = announcementDAO.findByAuthor(authorId);
        byAuthor.forEach(x -> announcementDAO.deleteById(x.getId()));
    }

    @Override
    public List<Author> findAll() {
        return authorDAO.findAll();
    }

    @Override
    public Author findById(int id) {
        return authorDAO.findById(id);
    }

    @Override
    public void save(Author author) {
        authorDAO.save(author);
    }

    @Override
    public void update(Author author) {
        authorDAO.update(author);
    }

    @Override
    public void deleteById(int id) {
        deleteAllAnnouncements(id);
        authorDAO.deleteById(id);
    }
}
