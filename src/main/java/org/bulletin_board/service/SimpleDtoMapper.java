package org.bulletin_board.service;

public interface SimpleDtoMapper<T, R> {
    R mapToDto(T entity);
    T mapToEntity(R dto);
}
