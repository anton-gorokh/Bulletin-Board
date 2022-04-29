package org.bulletin_board.repository;

import org.bulletin_board.domain.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author getByUsername(String username);
}
