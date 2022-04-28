package org.bulletin_board.repository;

import org.bulletin_board.domain.model.author.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email, Long> {
}
