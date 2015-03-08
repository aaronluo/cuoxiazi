/***********************************************
 * Filename       : BaseDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao;

import java.util.List;

/**
 * @Title: BaseDao
 * @Description: 数据访问对象接口
 * 
 * @version V1.0
 */
public interface BaseDao<T> {

    /**
     * 保存实体
     * 
     * @param entity
     *            待保存的实体
     * @return 操作结果
     */
    public long save(T entity);

    /**
     * 根据实体ID查找实体
     * 
     * @param id
     *            实体ID
     * @return 实体
     */
    public T get(Long id);

    /**
     * 更新实体
     * 
     * @param entity
     *            待更新的实体
     */
    public void update(T entity);

    /**
     * 删除实体
     * 
     * @param entity
     *            待更新的实体
     */
    public void delete(T entity);

    /**
     * 加载所有实体
     * 
     * @return 实体列表
     */
    public List<T> loadAll();

    /**
     * 返回实体数量
     * 
     * @return 实体数量
     */
    public int count();

    /**
     * 把指定的缓冲对象进行清除
     * 
     * @param entity
     *            待清除的实体
     */
    public void evict(T entity);

    /**
     * 新new一个对象，如果该对象设置了ID，则这个对象就当作游离态处理：
     * 当ID在数据库中不能找到时，用update的话肯定会报异常，然而用merge的话，就会insert。
     * 当ID在数据库中能找到的时候，update与merge的执行效果都是更新数据，发出update语句；
     * 如果没有设置ID的话，则这个对象就当作瞬态处理：
     * 用update的话，由于没有ID，所以会报异常，merge此时则会保存数据，根据ID生产策略生成一条数据；
     * 
     * @param entity
     *            待merge的实体
     */
    public void merge(T entity);

    /**
     * 获取实体列表
     * 
     * @param startIndex
     *            开始位置
     * @param pageSize
     *            分页大小
     * @param hql
     *            查询语句
     * @param values
     *            查询参数
     * @return 实体列表
     */
    public List<T> getPage(int startIndex, int pageSize, String hql,
            Object... values);

    /**
     * 保存或更新实体
     * 
     * @param entity
     *            待保存或更新的实体
     */
    public void saveOrUpdate(T entity);

    /**
     * 刷新实体
     * 
     * @param entity
     *            待刷新的实体
     */
    public void refresh(T entity);
}