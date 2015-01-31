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

@Transactional
public class HibernateBaseDao<T> extends HibernateDaoSupport implements
        BaseDao<T> {
    protected static final Logger log = Logger.getLogger(HibernateBaseDao.class);
    protected Class<T> entityClass;
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public HibernateBaseDao() {
        Class c = getClass();
        Type t = c.getGenericSuperclass();
        if( t instanceof ParameterizedType) {
            Type[] p = ((ParameterizedType)t).getActualTypeArguments();
            entityClass = (Class<T>)p[0];
        }
    }
    
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
    public long save(T entity) {
        return (Long) getHibernateTemplate().save(entity);
    }
    
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public void delete(T entity) {
        getHibernateTemplate().delete(entity);

    }
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
    @SuppressWarnings("unchecked")
    public T get(Integer id) {
        return (T)getHibernateTemplate().get(entityClass, (Integer)id);
    }

    @Transactional(propagation=Propagation.SUPPORTS, readOnly=false)
    public void update(T entity) {
        getHibernateTemplate().update(entity);
    }
    
    
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    @SuppressWarnings("unchecked")
    public List<T> loadAll() {
        
        String hql = "FROM " + entityClass.getName() + " AS entity GROUP BY entity.id ORDER BY entity.id";
        
        return getHibernateTemplate().find(hql);
    }
    
    @Transactional(propagation=Propagation.SUPPORTS, readOnly=true)
    public int count() {
        String hql = "select count(*) from " + entityClass.getName();
        
        return ((Number)getHibernateTemplate().find(hql).listIterator().next()).intValue();
    }

    public void evict(T entity){
        getHibernateTemplate().evict(entity);
    }
    
    public void merge(T entity){
        getHibernateTemplate().merge(entity);
    }
    
    @SuppressWarnings("unchecked")
    public List<T> getPage(final int startIndex, final int pageSize, final String hql,
            final Object... values) {
        return (List<T>) getHibernateTemplate().execute(new HibernateCallback(){

            public Object doInHibernate(final Session session)
                    throws HibernateException, SQLException {
                
                Query query = session.createQuery(hql);
                for(int i = 0; i < values.length; ++i) {
                    query.setParameter(i, values[i]);
                }
                
                query.setFirstResult(startIndex);
                query.setMaxResults(pageSize);
                
                return query.list();
                
            }
        });
    }
    
    public void saveOrUpdate(T entity){
        getHibernateTemplate().saveOrUpdate(entity);
    }

    public void refresh(T entity) {
        getHibernateTemplate().refresh(entity);
    }   
}
