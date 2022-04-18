package org.bulletin_board.service;


import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.board.Rubric;
import org.bulletin_board.repository.RubricRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class RubricService {
    final RubricRepository rubricRepository;

    public RubricService(RubricRepository rubricRepository) {
        this.rubricRepository = rubricRepository;
    }

    public Rubric findByName(String name) throws NullPointerException {
        return rubricRepository.findByName(name)
                .orElseThrow(() -> new NullPointerException("There is no rubric with such name"));
    }

    public List<Rubric> findAllContainingWord(String word) throws NullPointerException {
        List<Rubric> allByNameContaining = rubricRepository.findAllByNameContaining(word);
        if (allByNameContaining.isEmpty()) {
            throw new NullPointerException("There is no rubric containing such word");
        }
        return allByNameContaining;
    }

    public List<Rubric> findAll() {
        return rubricRepository.findAll();
    }

    public Rubric findById(Long id) {
        return rubricRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("There is no rubric with such id"));
    }

    public void save(Rubric rubric) {
        rubricRepository.save(rubric);
    }

    public void update(Rubric rubric) {
        rubricRepository.save(rubric);
    }

    public void deleteById(Long id) {
        rubricRepository.deleteById(id);
    }
}
