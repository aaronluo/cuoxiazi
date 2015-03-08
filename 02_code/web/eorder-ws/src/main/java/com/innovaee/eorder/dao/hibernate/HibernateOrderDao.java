/***********************************************
 * Filename       : HibernateOrderDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.support.DateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.util.Date;
import java.util.List;

/**
 * @Title: HibernateOrderDao
 * @Description: 订单数据访问对象实现类
 * 
 * @version V1.0
 */
public class HibernateOrderDao extends HibernateBaseDao<Order> implements
        OrderDao {

    /**
     * 根据用户ID得到订单列表
     * 
     * @param memberId
     *            用户ID
     * @return 订单列表
     */
    @Override
    public List<Order> getOrdersByMemberId(final Long memberId) {
        String hql = "from Order as order where order.member.id = ?";
        Object[] paras = { memberId };
        List<Order> orders = getPage(0, 999, hql, paras);
        return orders;
    }

    /**
     * 获得指定日期订单最大序号，则新订单以这个序号增1
     * @param date
     * @return 序号最大值，指定日期没有订单产生，则返回0
     */
    @Override
    public int getMaxOrderSeqofDate(final Date date) {
       //1. 根据指定日期，组合查询条件
        final Date beginDate = DateUtil.getFirstTimeOfDate(date);
        final Date endDate = DateUtil.getLastTimeOfDate(date);
        //2. 通过Hibernate查询
        return getHibernateTemplate().execute(new HibernateCallback<Integer>(){
            
            public Integer doInHibernate(Session session) {
                Criteria criteria = session.createCriteria(Order.class);
                
                criteria.add(Restrictions.ge("createDate", beginDate));
                criteria.add(Restrictions.le("createDate", endDate));
                
               return ((Long)criteria.setProjection(Projections.rowCount()).uniqueResult()).intValue();
            }
            
        });
    }
}