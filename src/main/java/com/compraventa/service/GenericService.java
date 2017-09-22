package com.compraventa.service;

import java.util.List;

/**
 * Generic Service
 */
public interface GenericService<E, K> {
    public void saveOrUpdate(E entity);

    public List<E> getAll();

    public E get(K id);

    public void add(E entity);
    
    public void persist(E entity) ;

    public void update(E entity);

    public void remove(E entity);
}
