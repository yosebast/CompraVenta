package com.compraventa.dao;
import java.util.List;

/**
 *  https://vrtoonjava.wordpress.com/2012/06/17/part-3-dao-and-service-layer/
 * @param <E>
 * @param <K>
 */
public interface GenericDao<E,K> {
    public void add(E entity) ;
    public void saveOrUpdate(E entity) ;
    public void update(E entity) ;
    public void persist(E entity) ;
    public void remove(E entity);
    public E find(K key);
    public List<E> getAll() ;
}