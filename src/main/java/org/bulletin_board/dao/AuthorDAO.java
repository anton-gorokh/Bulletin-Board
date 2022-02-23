package org.bulletin_board.dao;

import org.bulletin_board.domain.author.Author;

import java.util.List;

public interface AuthorDAO extends CrudDAO<Author> {
    List<Author> findAll();
}
