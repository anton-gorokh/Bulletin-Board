package org.bulletin_board.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.CrudDAO;
import org.bulletin_board.domain.MatchingAd;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchingAdDAOImpl implements CrudDAO<MatchingAd> {
    @PersistenceContext
    EntityManager em;

    @Override
    public MatchingAd findById(int id) {
        MatchingAd matchingAd = em.find(MatchingAd.class, id);
        return matchingAd;
    }

    @Override
    public void save(MatchingAd MatchingAd) {
        em.persist(MatchingAd);
    }

    @Override
    public void update(MatchingAd MatchingAd) {
        MatchingAd mergedMad = em.merge(MatchingAd);
        em.persist(mergedMad);
    }

    @Override
    public void deleteById(int id) {
        MatchingAd entity = em.getReference(MatchingAd.class, id);
        em.remove(entity);
    }
}
