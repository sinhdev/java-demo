package dev.sinhnx.threelayer.dal;

import java.util.List;

public interface DAL<E> {
    int insert(E e);
    E getById(E e);
    // int update(E e);
    // int delete(E e);
    List<E> search(E e);
    List<E> search(String whereClause);
}