package org.bulletin_board.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.author.Author;
import org.bulletin_board.repository.AuthorRepository;
import org.bulletin_board.service.AnnouncementService;
import org.bulletin_board.service.AuthorService;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@AllArgsConstructor
public class AuthorServiceImpl implements AuthorService {
    final
    AuthorRepository authorRepository;

    final
    AnnouncementService announcementService;

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(int id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("There is no author with such id"));
    }

    @Override
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void update(Author author) {
        authorRepository.save(author);
    }

    @Override
    public void deleteById(int id) {
        announcementService.deleteAllAnnouncementsByAuthorId(id);
        authorRepository.deleteById(id);
    }
}
