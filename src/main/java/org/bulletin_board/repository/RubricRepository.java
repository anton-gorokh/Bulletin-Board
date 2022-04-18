package org.bulletin_board.repository;

import org.bulletin_board.domain.board.Rubric;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RubricRepository extends JpaRepository<Rubric, Long> {
    Optional<Rubric> findByName(String name);

    List<Rubric> findAllByNameContaining(String word);
}
