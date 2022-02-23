package org.bulletin_board.service.impl;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.RubricDAO;
import org.bulletin_board.domain.Rubric;
import org.bulletin_board.service.RubricService;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class RubricServiceImpl implements RubricService {
    final RubricDAO rubricDAO;

    public RubricServiceImpl(RubricDAO rubricDAO) {
        this.rubricDAO = rubricDAO;
    }

    @Override
    public Rubric findByName(String name) {
        return rubricDAO.findByName(name);
    }

    @Override
    public List<Rubric> findAllContainingWord(String word) {
        return rubricDAO.findAllContainingWord(word);
    }

    @Override
    public List<Rubric> findAll() {
        return rubricDAO.findAll();
    }

    @Override
    public Rubric findById(int id) {
        return rubricDAO.findById(id);
    }

    @Override
    public void save(Rubric announcement) {
        rubricDAO.save(announcement);
    }

    @Override
    public void update(Rubric announcement) {
        rubricDAO.update(announcement);
    }

    @Override
    public void deleteById(int id) {
        rubricDAO.deleteById(id);
    }
}
