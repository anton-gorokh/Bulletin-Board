package org.bulletin_board.dao;

import org.bulletin_board.domain.Rubric;

import java.util.List;

public interface RubricDAO extends CrudDAO<Rubric> {

    Rubric findByName(String name);

    List<Rubric> findAllContainingWord(String word);

    List<Rubric> findAll();
}
