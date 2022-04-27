package org.bulletin_board.service;

public interface CrudService<T> {
    T getById(Long id);
    Long save(T dto);
    void update(T dto, Long id);
    void deleteById(Long id);
}
