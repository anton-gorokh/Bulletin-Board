package org.bulletin_board.dao.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.bulletin_board.dao.AuthorDAO;
import org.bulletin_board.domain.author.Author;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthorDAOImpl implements AuthorDAO {
    @PersistenceContext
    EntityManager em;

    @Override
    public List<Author> findAll() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Author> query = cb.createQuery(Author.class);
        Root<Author> root = query.from(Author.class);

        query.select(root);
        TypedQuery<Author> typedQuery = em.createQuery(query);
        return typedQuery.getResultList();
    }

    @Override
    public Author findById(int id) {
        return em.find(Author.class, id);
    }

    @Override
    public void save(Author author) {
        em.persist(author);
    }

    @Override
    public void update(Author author) {
        Author mergedAu = em.merge(author);
        em.persist(mergedAu);
    }

    @Override
    public void deleteById(int id) {
        Author entity = em.getReference(Author.class, id);
        em.remove(entity);
    }
}
