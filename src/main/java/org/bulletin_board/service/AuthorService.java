package org.bulletin_board.service;

import org.bulletin_board.domain.author.Author;

import java.util.List;

public interface AuthorService extends CrudService<Author> {
    List<Author> findAll();
}
