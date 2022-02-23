package org.bulletin_board.service;

import org.bulletin_board.domain.Rubric;

import java.util.List;

public interface RubricService extends CrudService<Rubric> {
    List<Rubric> findAll();

    Rubric findByName(String name);

    List<Rubric> findAllContainingWord(String word);
}
