package com.innovaee.eorder.dao;

import java.util.List;

public interface BaseDao<T> {
    public long save(T entity);

    public void update(T entity);
    
    public void delete(T entity);
    
    public T get(Integer id);
    
    public List<T> loadAll();
    
    public int count();
    
    public void evict(T entity);
    
    public void merge(T entity);
    
    public List<T> getPage(int startIndex, int pageSize, String hql, Object...values);
    
    public void saveOrUpdate(T entity);
    
    public void refresh(T entity);
}