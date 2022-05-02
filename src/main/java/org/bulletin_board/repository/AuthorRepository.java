package org.bulletin_board.repository;

import org.bulletin_board.domain.model.Author;
import org.bulletin_board.domain.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author getByUsername(String username);
}
