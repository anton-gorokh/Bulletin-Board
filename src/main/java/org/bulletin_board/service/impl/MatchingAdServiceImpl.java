package org.bulletin_board.service.impl;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.CrudDAO;
import org.bulletin_board.domain.MatchingAd;
import org.bulletin_board.service.CrudService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class MatchingAdServiceImpl implements CrudService<MatchingAd> {

    final
    @Qualifier("matchingAdDAOImpl")
    CrudDAO<MatchingAd> matchingAdDAO;

    @Override
    public MatchingAd findById(int id) {
        return matchingAdDAO.findById(id);
    }

    @Override
    public void save(MatchingAd matchingAd) {
        matchingAdDAO.save(matchingAd);
    }

    @Override
    public void update(MatchingAd matchingAd) {
        matchingAdDAO.update(matchingAd);
    }

    @Override
    public void deleteById(int id) {
        matchingAdDAO.deleteById(id);
    }

}
