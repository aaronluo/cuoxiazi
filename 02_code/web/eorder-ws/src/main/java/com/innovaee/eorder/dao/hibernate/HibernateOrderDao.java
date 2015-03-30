/***********************************************
 * Filename       : HibernateOrderDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao.hibernate;

import com.innovaee.eorder.dao.OrderDao;
import com.innovaee.eorder.entity.Order;
import com.innovaee.eorder.support.Constants;
import com.innovaee.eorder.support.DateUtil;
import com.innovaee.eorder.vo.NewOrderVO;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Property;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.AliasToBeanResultTransformer;
import org.springframework.orm.hibernate3.HibernateCallback;

import java.sql.SQLException;
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
        String hql = "from Order as order where order.member.id = ? ORDER BY order.id desc";
        Object[] paras = { memberId };
        List<Order> orders = getPage(0, 999, hql, paras);
        return orders;
    }

    /**
     * 获得指定日期订单最大序号，则新订单以这个序号增1
     * 
     * @param date
     * @return 序号最大值，指定日期没有订单产生，则返回0
     */
    @Override
    public int getMaxOrderSeqofDate(final Date date) {
        // 1. 根据指定日期，组合查询条件
        final Date beginDate = DateUtil.getFirstTimeOfDate(date);
        final Date endDate = DateUtil.getLastTimeOfDate(date);
        // 2. 通过Hibernate查询
        return getHibernateTemplate().execute(new HibernateCallback<Integer>() {

            public Integer doInHibernate(Session session)
                    throws HibernateException, SQLException {
                Criteria criteria = session.createCriteria(Order.class);

                criteria.add(Restrictions.ge("createDate", beginDate));
                criteria.add(Restrictions.le("createDate", endDate));

                return ((Long) criteria.setProjection(Projections.rowCount())
                        .uniqueResult()).intValue();
            }

        });
    }

    /**
     * 查询符合查询条件的订单总数
     * 
     * @param orderCriteria
     *            查询条件，包括：
     *            1. 订单序号 - like查询
     *            2. 服务员ID
     *            3. 收银员ID
     *            4. 会员ID
     *            5. 创建时间
     *            6. 订单状态
     *            7. 订单总价
     * @return 查询记录总数
     */
    @Override
    public int count(final NewOrderVO orderCriteria) {
        
       return  getHibernateTemplate().execute(new HibernateCallback<Integer>(){

            @Override
            public Integer doInHibernate(Session session)
                    throws HibernateException, SQLException {
               
                Criteria criteria = session.createCriteria(Order.class);
                
                //订单序号模糊查询
                if(orderCriteria.getOrderSeq() != null && !orderCriteria.getOrderSeq().isEmpty()) {
                    criteria.add(Restrictions.like("orderSeq", orderCriteria.getOrderSeq(), MatchMode.ANYWHERE));
                }
                //服务员ID
                if(null != orderCriteria.getServentId() && orderCriteria.getServentId() > 0L) {
                    criteria.createAlias("servent", "servent");
                    criteria.add(Restrictions.eq("servent.id", orderCriteria.getServentId()));
                }
                //会员ID
                if(null != orderCriteria.getMemberId()  && orderCriteria.getMemberId() > 0L) {
                    criteria.createAlias("member", "member");
                    criteria.add(Restrictions.eq("member.id", orderCriteria.getMemberId()));
                }
                 //收银员ID
                if(null != orderCriteria.getCashierId() && orderCriteria.getCashierId() > 0L) {
                    criteria.createAlias("casher", "casher");
                    criteria.add(Restrictions.eq("casher.id", orderCriteria.getCashierId()));
                }
                //创建时间
                if(orderCriteria.getCreateAtMin() != null) {
                    criteria.add(Restrictions.ge("createDate", orderCriteria.getCreateAtMin()));
                }
                if(orderCriteria.getCreateAtMax() != null) {
                    criteria.add(Restrictions.le("createDate", orderCriteria.getCreateAtMax()));
                }   
                //订单状态
                if(orderCriteria.getStatus() >= Constants.ORDER_NEW) {
                    criteria.add(Restrictions.eq("orderStatus", orderCriteria.getStatus()));
                }
                //订单总价
                if(orderCriteria.getTotalPriceMin() > 0) {
                    criteria.add(Restrictions.ge("totalPrice", orderCriteria.getTotalPriceMin()));
                }
                if(orderCriteria.getTotalPriceMax() > 0 
                        && orderCriteria.getTotalPriceMax() > orderCriteria.getTotalPriceMin()) {
                    criteria.add(Restrictions.le("totalPrice", orderCriteria.getTotalPriceMax()));
                }
                
                criteria.setProjection(Projections.projectionList().add(Projections.count("id")));
                
                return ((Long)criteria.list().iterator().next()).intValue();
            }
            
        });
    }
    /**
     * 查询符合查询条件的订单分页数据
     * @param orderCriteria
     *            查询条件，包括：
     *            1. 订单序号 - like查询
     *            2. 服务员ID
     *            3. 收银员ID
     *            4. 会员ID
     *            5. 创建时间
     *            6. 订单状态
     *            7. 订单总价
     * @param curPage 当前页
     * @param pageSize 分页大小
     * @return
     */
    @Override
    public List<Order> query(final NewOrderVO orderCriteria, final int curPage, final int pageSize) {
        return  getHibernateTemplate().execute(new HibernateCallback<List<Order>>(){

            @SuppressWarnings("unchecked")
            @Override
            public List<Order> doInHibernate(Session session)
                    throws HibernateException, SQLException {
                
                int startIdx = (curPage - 1) * pageSize;
                Criteria criteria = session.createCriteria(Order.class);
                
                if(null == orderCriteria) {
                    return getPage(startIdx, pageSize, "FROM Order as order ORDER BY id DESC");
                }
                
                //订单序号模糊查询
                if(orderCriteria.getOrderSeq() != null && !orderCriteria.getOrderSeq().isEmpty()) {
                    criteria.add(Restrictions.like("orderSeq", orderCriteria.getOrderSeq(), MatchMode.ANYWHERE));
                }
                //服务员ID
                if(null != orderCriteria.getServentId() && orderCriteria.getServentId() > 0L) {
                    criteria.createAlias("servent", "servent");
                    criteria.add(Restrictions.eq("servent.id", orderCriteria.getServentId()));
                }
                //会员ID
                if(null != orderCriteria.getMemberId()  && orderCriteria.getMemberId() > 0L) {
                    criteria.createAlias("member", "member");
                    criteria.add(Restrictions.eq("member.id", orderCriteria.getMemberId()));
                }
                 //收银员ID
                if(null != orderCriteria.getCashierId() && orderCriteria.getCashierId() > 0L) {
                    criteria.createAlias("casher", "casher");
                    criteria.add(Restrictions.eq("casher.id", orderCriteria.getCashierId()));
                }
                //创建时间
                if(orderCriteria.getCreateAtMin() != null) {
                    criteria.add(Restrictions.ge("createDate", orderCriteria.getCreateAtMin()));
                }
                if(orderCriteria.getCreateAtMax() != null) {
                    criteria.add(Restrictions.le("createDate", orderCriteria.getCreateAtMax()));
                }   
                //订单状态
                if(orderCriteria.getStatus() >= Constants.ORDER_NEW) {
                    criteria.add(Restrictions.eq("orderStatus", orderCriteria.getStatus()));
                }
                //订单总价
                if(orderCriteria.getTotalPriceMin() > 0) {
                    criteria.add(Restrictions.ge("totalPrice", orderCriteria.getTotalPriceMin()));
                }
                if(orderCriteria.getTotalPriceMax() > 0 
                        && orderCriteria.getTotalPriceMax() > orderCriteria.getTotalPriceMin()) {
                    criteria.add(Restrictions.le("totalPrice", orderCriteria.getTotalPriceMax()));
                }

//               criteria.createAlias("orderItems", "orderItems");
               criteria.addOrder(org.hibernate.criterion.Order.desc("this.id"));
               criteria.setProjection(
                       Projections.projectionList()
                       .add(Projections.distinct(Projections.property("id")))
                       .add(Property.forName("id").as("id"))
                       .add(Property.forName("attendeeNumber").as("attendeeNumber"))
                       .add(Property.forName("casher").as("casher"))
                       .add(Property.forName("discountPrice").as("discountPrice"))
                       .add(Property.forName("member").as("member"))
                       .add(Property.forName("orderSeq").as("orderSeq"))
                       .add(Property.forName("orderStatus").as("orderStatus"))
                       .add(Property.forName("servent").as("servent"))
                       .add(Property.forName("tableNumber").as("tableNumber"))
                       .add(Property.forName("totalPrice").as("totalPrice"))
                       .add(Property.forName("createDate").as("createDate"))
                       .add(Property.forName("updateDate").as("updateDate")))
//                       .add(Property.forName("orderItems").as("orderItems")))
                       .setResultTransformer(new AliasToBeanResultTransformer(Order.class));

                criteria.setFirstResult(startIdx);
                criteria.setMaxResults(pageSize);
                
                return criteria.list();
            }
        });
    }
}