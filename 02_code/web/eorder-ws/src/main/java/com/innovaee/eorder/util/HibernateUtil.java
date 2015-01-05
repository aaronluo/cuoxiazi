/***********************************************
 * Filename        : HibernateUtil.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.util;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.internal.StandardServiceRegistryImpl;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Title: HibernateUtil
 * @Description: Hibernate 工具类
 * @version V1.0
 */
public class HibernateUtil {

    /** 日志对象 */
    protected static final Logger LOGGER = LoggerFactory
            .getLogger(HibernateUtil.class);

    /** Hibernate配置文件 */
    private static String CONFIG_FILE_LOCATION = "hibernate.cfg.xml";

    /** 单例 Session */
    private static final ThreadLocal<Session> threadLocal = new ThreadLocal<Session>();

    /** 单例 Transaction */
    private static final ThreadLocal<Transaction> threadTransaction = new ThreadLocal<Transaction>();

    /** 单例 SessionFactory */
    private static SessionFactory sessionFactory;

    /**
     * 返回本地线程中的Session实例，如果需要，延迟加载SessionFactory。
     * 
     * @return Session
     * @throws HibernateException
     */
    public static Session getSession() throws HibernateException {
        Session session = (Session) threadLocal.get();

        if (session == null) {
            if (sessionFactory == null) {
                try {
                    Configuration configuration = new Configuration()
                            .configure(CONFIG_FILE_LOCATION);
                    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                            .applySettings(configuration.getProperties());
                    StandardServiceRegistryImpl serviceRegistry = (StandardServiceRegistryImpl) builder
                            .build();
                    sessionFactory = configuration
                            .buildSessionFactory(serviceRegistry);
                } catch (Exception e) {
                    LOGGER.error("创建Session工厂失败： " + e.getMessage());
                }
            }
            if (null != sessionFactory) {
                session = sessionFactory.openSession();
            }
            threadLocal.set(session);
        }
        return session;
    }

    /**
     * 关闭单例的Hibernate Session实例
     * 
     * @throws HibernateException
     */
    public static void closeSession() throws HibernateException {
        Session session = (Session) threadLocal.get();
        threadLocal.set(null);
        if (session != null) {
            HibernateUtil.closeSession();
        }
    }

    /**
     * 默认构造函数
     */
    private HibernateUtil() {
    }

    /**
     * 开始事务
     * 
     * @throws HibernateException
     */
    public static void beginTransaction() throws HibernateException {
        Transaction tx = (Transaction) threadTransaction.get();
        try {
            if (tx == null) {
                tx = getSession().beginTransaction();
                threadTransaction.set(tx);
            }
        } catch (HibernateException ex) {
            LOGGER.error("开始事务失败： " + ex.getMessage());
            throw new HibernateException(ex.toString());
        }
    }

    /**
     * 提交事务
     * 
     * @throws HibernateException
     */
    public static void commitTransaction() throws HibernateException {
        Transaction tx = (Transaction) threadTransaction.get();
        try {
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack())
                tx.commit();
            threadTransaction.set(null);
        } catch (HibernateException ex) {
            LOGGER.error("提交事务失败： " + ex.getMessage());
            rollbackTransaction();
            throw new HibernateException(ex.toString());
        }
    }

    /**
     * 回滚事务
     * 
     * @throws HibernateException
     */
    public static void rollbackTransaction() throws HibernateException {
        Transaction tx = (Transaction) threadTransaction.get();
        try {
            threadTransaction.set(null);
            if (tx != null && !tx.wasCommitted() && !tx.wasRolledBack()) {
                tx.rollback();
            }
        } catch (HibernateException ex) {
            LOGGER.error("回滚事务失败： " + ex.getMessage());
            throw new HibernateException(ex.toString());
        } finally {
            closeSession();
        }
    }
}