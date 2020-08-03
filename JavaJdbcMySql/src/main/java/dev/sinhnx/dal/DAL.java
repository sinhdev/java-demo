package dev.sinhnx.dal;

import java.util.List;

public interface DAL<E> {
    int insert(E e);

    // int update(E e);
    // int delete(E e);
    // List<E> search(E e);
}