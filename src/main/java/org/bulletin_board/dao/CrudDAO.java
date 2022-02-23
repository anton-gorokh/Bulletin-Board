package org.bulletin_board.dao;

public interface CrudDAO<T> {

    T findById(int id);

    void save(T t);

    void update(T t);

    void deleteById(int id);
}