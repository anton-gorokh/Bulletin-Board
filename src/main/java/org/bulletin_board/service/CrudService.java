package org.bulletin_board.service;

public interface CrudService<T> {

    T findById(int id);

    void save(T t);

    void update(T t);

    void deleteById(int id);
}
