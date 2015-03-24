/***********************************************
 * Filename       : HibernateDishDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.entity.Dish;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

/**
 * @Title: HibernateDishDao
 * @Description: 菜品数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateDishDao extends HibernateBaseDao<Dish> implements DishDao {

    /**
     * 根据菜品名称获取菜品对象
     * @param dishName 菜品名称
     * @return 返回菜品对象或者null
     */
    @Override
    public Dish getDishByName(final String dishName) {
        return getHibernateTemplate().execute(new HibernateCallback<Dish>(){

            @Override
            public Dish doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Dish dish = null;
                
                Criteria criteria = session.createCriteria(Dish.class);
                criteria.add(Restrictions.eq("name", dishName));
                criteria.add(Restrictions.eq("onSell", true));
               
                if ( !criteria.list().isEmpty()) {
                    dish = (Dish)criteria.list().get(0);
                }

                return dish;
            }});
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Dish> loadAll() {
        final String hql = "FROM Dish AS dish WHERE dish.onSell=?";
        Object[] params = new Object[1];
        params[0] = true;
        
        return getHibernateTemplate().find(hql, params);
    }
}