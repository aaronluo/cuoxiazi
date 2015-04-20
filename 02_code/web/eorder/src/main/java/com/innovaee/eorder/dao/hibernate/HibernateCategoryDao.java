/***********************************************
 * Filename       : HibernateCategoryDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import com.innovaee.eorder.dao.CategoryDao;
import com.innovaee.eorder.entity.Category;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

/**
 * @Title: HibernateCategoryDao
 * @Description: 分类数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateCategoryDao extends HibernateBaseDao<Category> implements
        CategoryDao {

    /**
     * 根据指定菜品分类名称查找菜品分类
     * 
     * @param name
     *            菜品分类名称
     * @return 菜品分类对象或者null
     */
    
    public Category getCategoryByName(final String name) {

        return getHibernateTemplate().execute(
                new HibernateCallback<Category>() {
                    public Category doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        Category category = null;
                        Criteria criteria = session
                                .createCriteria(Category.class);

                        criteria.add(Restrictions.eq("name", name));

                        if (!criteria.list().isEmpty()) {
                            category = (Category) criteria.uniqueResult();
                        }

                        return category;
                    }
                });
    }

    
    public List<Category> getPage(int startIndex, int pageSize) {
        return this.getPage(startIndex, pageSize, "FROM category");
    }
}