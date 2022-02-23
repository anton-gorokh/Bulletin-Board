package org.bulletin_board.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.RubricDAO;
import org.bulletin_board.domain.Rubric;
import org.bulletin_board.domain.Rubric_;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RubricDAOImpl implements RubricDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Rubric> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Rubric> query = cb.createQuery(Rubric.class);
        Root<Rubric> root = query.from(Rubric.class);

        query.select(root);
        TypedQuery<Rubric> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public Rubric findById(int id) {
        Rubric rubric = em.find(Rubric.class, id);
        return rubric;
    }

    @Override
    public void save(Rubric rubric) {
        em.persist(rubric);
    }

    @Override
    public void update(Rubric rubric) {
        Rubric mergedRubric = em.merge(rubric);
        em.persist(mergedRubric);
    }

    @Override
    public void deleteById(int id) {
        Rubric entity = em.getReference(Rubric.class, id);
        em.remove(entity);
    }

    @Override
    public Rubric findByName(String name) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Rubric> query = cb.createQuery(Rubric.class);
        Root<Rubric> root = query.from(Rubric.class);

        query.select(root).where(cb.like(root.get(Rubric_.name), String.format("%%%s%%", name)));
        TypedQuery<Rubric> typedQuery = em.createQuery(query);
        return typedQuery.getSingleResult();
    }

    @Override
    public List<Rubric> findAllContainingWord(String word) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Rubric> query = cb.createQuery(Rubric.class);
        Root<Rubric> root = query.from(Rubric.class);

        Predicate name = cb.like(root.get(Rubric_.name), String.format("%%%s%%", word)); //pattern: %word%

        query.select(root).where(name);
        TypedQuery<Rubric> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }
}
