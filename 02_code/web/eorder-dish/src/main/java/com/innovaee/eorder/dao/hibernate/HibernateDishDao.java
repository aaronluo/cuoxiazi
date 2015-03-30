/***********************************************
 * Filename       : HibernateDishDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.innovaee.eorder.dao.DishDao;
import com.innovaee.eorder.entity.Dish;

/**
 * @Title: HibernateDishDao
 * @Description: 菜品数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateDishDao extends HibernateBaseDao<Dish> implements DishDao {

    /**
     * 根据菜品名称获取菜品对象
     * 
     * @param dishName
     *            菜品名称
     * @return 返回菜品对象或者null
     */

    public Dish getDishByName(final String dishName) {
        return getHibernateTemplate().execute(new HibernateCallback<Dish>() {

            public Dish doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Dish dish = null;

                Criteria criteria = session.createCriteria(Dish.class);
                criteria.add(Restrictions.eq("dishName", dishName));
                criteria.add(Restrictions.eq("onSell", true));

                if (!criteria.list().isEmpty()) {
                    dish = (Dish) criteria.list().get(0);
                }

                return dish;
            }
        });
    }

    /**
     * 根据分类ID获取菜品记录条数
     * 
     * @param categoryId
     *            分类ID
     * @return 返回菜品记录条数
     */
    @SuppressWarnings("rawtypes")
    public Integer getDishCountById(final Long categoryId) {
        String queryString = "select count(*) from Dish as d where d.category.id=:categoryId and d.onSell=true";

        String paramName = "categoryId";

        // String value = "xiyue";

        List list = this.getHibernateTemplate().findByNamedParam(queryString,
                paramName, categoryId);

        System.out.println(list.get(0));

        return Integer.parseInt(list.get(0).toString());
    }

}