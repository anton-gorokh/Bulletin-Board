package org.bulletin_board.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.AnnouncementDAO;
import org.bulletin_board.dao.AuthorDAO;
import org.bulletin_board.domain.Announcement;
import org.bulletin_board.domain.Announcement_;
import org.bulletin_board.domain.Rubric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AnnouncementDAOImpl implements AnnouncementDAO {
    @PersistenceContext
    EntityManager em;

    @Autowired
    AuthorDAO authorDAO;

    @Override
    public Announcement findById(int id) {
        Announcement announcement = em.find(Announcement.class, id);
        return announcement;
    }

    @Override
    public void save(Announcement announcement) {
        em.persist(announcement);
    }

    @Override
    public void update(Announcement announcement) {
        Announcement mergedAn = em.merge(announcement);
        em.persist(mergedAn);
    }

    @Override
    public void deleteById(int id) {
        Announcement entity = em.getReference(Announcement.class, id);
        em.remove(entity);
    }

    @Override
    public List<Announcement> findByAuthor(int authorId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Announcement> query = cb.createQuery(Announcement.class);
        Root<Announcement> root = query.from(Announcement.class);

        query.select(root).where(cb.equal(root.get(Announcement_.author), authorDAO.findById(authorId)));
        TypedQuery<Announcement> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Announcement> findByRubrics(List<Rubric> rubrics) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<Announcement> resultList = new ArrayList<>();
        rubrics.forEach(rubric -> {
            CriteriaQuery<Announcement> query = cb.createQuery(Announcement.class);
            Root<Announcement> root = query.from(Announcement.class);

            query.select(root).where(cb.equal(root.get(Announcement_.rubric), rubric));
            resultList.addAll(em.createQuery(query).getResultList());
        });
        return resultList;
    }

    @Override
    public List<Announcement> findBetweenTwoDates(Date from, Date to) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Announcement> query = cb.createQuery(Announcement.class);
        Root<Announcement> root = query.from(Announcement.class);

        query.select(root).where(cb.between(root.get(Announcement_.creationDate), from, to));
        TypedQuery<Announcement> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Announcement> findByPriceRange(BigDecimal priceFrom, BigDecimal priceTo) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Announcement> query = cb.createQuery(Announcement.class);
        Root<Announcement> root = query.from(Announcement.class);

        query.select(root).where(cb.between(root.get(Announcement_.pay), priceFrom, priceTo));
        TypedQuery<Announcement> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public List<Announcement> findAllContainingWord(String word) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Announcement> query = cb.createQuery(Announcement.class);
        Root<Announcement> root = query.from(Announcement.class);

        Predicate name = cb.like(root.get(Announcement_.name), String.format("%%%s%%", word)); //pattern: %word%
        Predicate text = cb.like(root.get(Announcement_.text), String.format("%%%s%%", word)); //pattern: %word%

        query.select(root).where(cb.or(name, text));
        TypedQuery<Announcement> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }
}
