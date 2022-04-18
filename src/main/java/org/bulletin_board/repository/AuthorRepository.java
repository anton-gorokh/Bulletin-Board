package org.bulletin_board.repository;

import org.bulletin_board.domain.board.author.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Author getByUserName(String userName);
}
