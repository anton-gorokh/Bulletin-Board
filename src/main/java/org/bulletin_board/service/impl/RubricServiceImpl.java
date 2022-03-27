package org.bulletin_board.service.impl;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.Rubric;
import org.bulletin_board.repository.RubricRepository;
import org.bulletin_board.service.RubricService;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class RubricServiceImpl implements RubricService {
    final RubricRepository rubricRepository;

    public RubricServiceImpl(RubricRepository rubricRepository) {
        this.rubricRepository = rubricRepository;
    }

    @Override
    public Rubric findByName(String name) throws NullPointerException {
        return rubricRepository.findByName(name)
                .orElseThrow(() -> new NullPointerException("There is no rubric with such name"));
    }

    @Override
    public List<Rubric> findAllContainingWord(String word) throws NullPointerException {
        List<Rubric> allByNameContaining = rubricRepository.findAllByNameContaining(word);
        if (allByNameContaining.isEmpty()) {
            throw new NullPointerException("There is no rubric containing such word");
        }
        return allByNameContaining;
    }

    @Override
    public List<Rubric> findAll() {
        return rubricRepository.findAll();
    }

    @Override
    public Rubric findById(int id) {
        return rubricRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("There is no rubric with such id"));
    }

    @Override
    public void save(Rubric rubric) {
        rubricRepository.save(rubric);
    }

    @Override
    public void update(Rubric rubric) {
        rubricRepository.save(rubric);
    }

    @Override
    public void deleteById(int id) {
        rubricRepository.deleteById(id);
    }
}
