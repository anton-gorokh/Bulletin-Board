package org.bulletin_board.repository;

import org.bulletin_board.domain.model.author.Phone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
