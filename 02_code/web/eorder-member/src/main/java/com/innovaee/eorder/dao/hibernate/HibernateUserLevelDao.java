/***********************************************
 * Filename       : HibernateUserLevelDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import com.innovaee.eorder.dao.UserLevelDao;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
import java.util.List;

/**
 * @Title: HibernateUserLevelDao
 * @Description: 用户等级数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateUserLevelDao extends HibernateBaseDao<UserLevel>
        implements UserLevelDao {

    /**
     * 根据指定名称查找用户等级信息
     * @param name
     * @return
     */
    public UserLevel getUserLevelByName(final String name) {
        return getHibernateTemplate().execute(
                new HibernateCallback<UserLevel>() {
                    public UserLevel doInHibernate(Session session)
                            throws HibernateException, SQLException {
                        UserLevel userLevel = null;
                        Criteria criteria = session.createCriteria(UserLevel.class);

                        criteria.add(Restrictions.eq("levelName", name));
                        criteria.add(Restrictions.eq("levelStatus", true));

                        if (!criteria.list().isEmpty()) {
                            userLevel = (UserLevel) criteria.list().iterator().next();
                        }

                        return userLevel;
                    }
                });
    }
    
    @SuppressWarnings("unchecked")
    public List<UserLevel> loadAll() {
        final String hql = "FROM UserLevel AS level WHERE level.levelStatus=? ORDER BY level.id DESC";
        Object[] params = new Object[1];
        params[0] = true;
        
        return getHibernateTemplate().find(hql, params);
    }
    /**
     * 获取当前用户等级的下一个等级
     * @param id
     * @return
     */
    public UserLevel getNextLevel(final Long id) {
        final UserLevel userLevel = this.get(id);
        UserLevel nextUserLevel = null;
        
        if(userLevel != null) {
            nextUserLevel = getHibernateTemplate().execute(new HibernateCallback<UserLevel>(){
                public UserLevel doInHibernate(Session session){
                    UserLevel newUserLevel = null;
                    
                    Criteria criteria = session.createCriteria(UserLevel.class);
                    criteria.add(Restrictions.gt("levelScore", userLevel.getLevelScore()));
                    criteria.addOrder(Order.asc("levelScore"));
                    
                    if(!criteria.list().isEmpty()) {
                        newUserLevel = (UserLevel)criteria.list().iterator().next();
                    }
                    
                    return newUserLevel;
                }
            });
        }
        
        return nextUserLevel;
    }
    /**
     * 获取当前用户等级的上一个等级
     * @param id
     * @return
     */
    public UserLevel getLastLevel(final Long id) {
        final UserLevel userLevel = this.get(id);
        UserLevel lastUserLevel = null;
        
        if(userLevel != null) {
            lastUserLevel = getHibernateTemplate().execute(new HibernateCallback<UserLevel>(){
                public UserLevel doInHibernate(Session session){
                    UserLevel newUserLevel = null;
                    
                    Criteria criteria = session.createCriteria(UserLevel.class);
                    criteria.add(Restrictions.lt("levelScore", userLevel.getLevelScore()));
                    criteria.addOrder(Order.desc("levelScore"));
                    
                    if(!criteria.list().isEmpty()) {
                        newUserLevel = (UserLevel)criteria.list().iterator().next();
                    }
                    
                    return newUserLevel;
                }
            });
        }
        
        return lastUserLevel;
    }

    public List<User> getUsers(final Long userLevelId, final int curPage, final int pageSize) {
        
        return getHibernateTemplate().execute(new HibernateCallback<List<User>>(){
            @SuppressWarnings("unchecked")
            public List<User> doInHibernate (Session session) {
                Criteria criteria = session.createCriteria(User.class);
                criteria.createAlias("memberShip", "memberShip");
                criteria.createAlias("memberShip.level", "level");
                criteria.createAlias("roles", "role");
                criteria.add(Restrictions.eq("level.id", userLevelId));
                criteria.addOrder(Order.desc("id"));
                criteria.setProjection(
                        Projections.projectionList()
                        .add(Projections.distinct(Projections.property("id")))
                        .add(Property.forName("id").as("id"))
                        .add(Property.forName("cellphone").as("cellphone"))
                        .add(Property.forName("memberShip").as("memberShip"))
                        .add(Property.forName("username").as("username"))
                        .add(Property.forName("userStatus").as("userStatus")))
                        .setResultTransformer(new AliasToBeanResultTransformer(User.class));
                
                criteria.setFirstResult((curPage - 1) *  pageSize);
                criteria.setMaxResults(pageSize);
                
                return (List<User>)criteria.list();
            }
        });
    }
}