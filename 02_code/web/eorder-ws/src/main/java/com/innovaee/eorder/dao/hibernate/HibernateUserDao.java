/***********************************************
 * Filename       : HibernateUserDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.entity.User;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

/**
 * @Title: HibernateUserDao
 * @Description: 用户数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateUserDao extends HibernateBaseDao<User> implements UserDao {

    /**
     * 根据手机号码得到用户
     * 
     * @param cellphone
     *            手机号码
     * @return 用户
     */
    public User getUserByCellphone(final String cellphone) {
        return getHibernateTemplate().execute(new HibernateCallback<User>(){
            
            public User doInHibernate(Session session) {
                Criteria criteria = session.createCriteria(User.class);
                
                criteria.add(Restrictions.eq("cellphone", cellphone));
                
                if(criteria.list().iterator().hasNext()) {
                    return (User)(criteria.list().iterator().next());
                } else {
                    return null;
                }
            }
        });
        
    }

}