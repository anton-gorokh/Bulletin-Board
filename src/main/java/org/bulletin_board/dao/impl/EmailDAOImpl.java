package org.bulletin_board.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.EmailDAO;
import org.bulletin_board.domain.Announcement;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailDAOImpl implements EmailDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<String> findEmails(Announcement announcement) {
        TypedQuery<String> query = em.createQuery(
                "SELECT e.name " +
                        " FROM MatchingAd mad "
                        + " JOIN mad.author a "
                        + " JOIN a.emails e "
                        + " JOIN mad.rubric r "
                        + " WHERE r.name = :r_name "
                        + " AND (:an_pay BETWEEN mad.priceFrom AND mad.priceTo)", String.class);
        query.setParameter("an_pay", announcement.getPay());
        query.setParameter("r_name", announcement.getRubric().getName());
        return query.getResultList();
    }
}
