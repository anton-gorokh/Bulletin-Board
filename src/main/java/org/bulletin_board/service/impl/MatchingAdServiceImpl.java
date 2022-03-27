package org.bulletin_board.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.domain.MatchingAd;
import org.bulletin_board.repository.MatchingAdRepository;
import org.bulletin_board.service.CrudService;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class MatchingAdServiceImpl implements CrudService<MatchingAd> {

    MatchingAdRepository repository;

    @Override
    public MatchingAd findById(int id) {
        return repository.findById(id).get();
    }

    @Override
    public void save(MatchingAd matchingAd) {
        repository.save(matchingAd);
    }

    @Override
    public void update(MatchingAd matchingAd) {
        repository.save(matchingAd);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

}
