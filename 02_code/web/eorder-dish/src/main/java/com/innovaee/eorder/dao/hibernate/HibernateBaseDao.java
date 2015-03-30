/***********************************************
 * Filename       : HibernateBaseDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import com.innovaee.eorder.dao.BaseDao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.SQLException;
import java.util.List;

/**
 * @Title: HibernateBaseDao
 * @Description: 数据访问对象接口
 * 
 * @version V1.0
 */
@Transactional
public class HibernateBaseDao<T> extends HibernateDaoSupport implements
        BaseDao<T> {

    /** 日志对象 */
    protected static final Logger log = Logger
            .getLogger(HibernateBaseDao.class);

    /** 实体的类属性 */
    protected Class<T> entityClass;

    /**
     * 构造函数
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HibernateBaseDao() {
        Class clazz = getClass();
        Type type = clazz.getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) type).getActualTypeArguments();
            entityClass = (Class<T>) params[0];
        }
    }

    /**
     * 保存实体
     * 
     * @param entity
     *            待保存的实体
     * @return 操作结果
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public long save(T entity) {
        return (Long) getHibernateTemplate().save(entity);
    }

    /**
     * 删除实体
     * 
     * @param entity
     *            待更新的实体
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);
    }

    /**
     * 根据实体ID查找实体
     * 
     * @param id
     *            实体ID
     * @return 实体
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public T get(Long id) {
        return (T) getHibernateTemplate().get(entityClass, (Long) id);
    }

    /**
     * 更新实体
     * 
     * @param entity
     *            待更新的实体
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = false)
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }

    /**
     * 加载所有实体
     * 
     * @return 实体列表
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    @SuppressWarnings("unchecked")
    public List<T> loadAll() {
        String hql = "FROM " + entityClass.getName()
                + " AS entity GROUP BY entity.id ORDER BY entity.id";

        return getHibernateTemplate().find(hql);
    }

    /**
     * 返回实体数量
     * 
     * @return 实体数量
     */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public int count() {
        String hql = "select count(*) from " + entityClass.getName();

        return ((Number) getHibernateTemplate().find(hql).listIterator().next())
                .intValue();
    }

    /**
     * 把指定的缓冲对象进行清除
     * 
     * @param entity
     *            待清除的实体
     */
    public void evict(T entity) {
        getHibernateTemplate().evict(entity);
    }

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
    public void merge(T entity) {
        getHibernateTemplate().merge(entity);
    }

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
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> getPage(final int startIndex, final int pageSize,
            final String hql, final Object... values) {
        return (List<T>) getHibernateTemplate().execute(
                new HibernateCallback() {

                    public Object doInHibernate(final Session session)
                            throws HibernateException, SQLException {

                        Query query = session.createQuery(hql);
                        for (int i = 0; i < values.length; ++i) {
                            query.setParameter(i, values[i]);
                        }

                        query.setFirstResult(startIndex);
                        query.setMaxResults(pageSize);

                        return query.list();
                    }
                });
    }

    /**
     * 保存或更新实体
     * 
     * @param entity
     *            待保存或更新的实体
     */
    public void saveOrUpdate(T entity) {
        getHibernateTemplate().saveOrUpdate(entity);
    }

    /**
     * 刷新实体
     * 
     * @param entity
     *            待刷新的实体
     */
    public void refresh(T entity) {
        getHibernateTemplate().refresh(entity);
    }
}