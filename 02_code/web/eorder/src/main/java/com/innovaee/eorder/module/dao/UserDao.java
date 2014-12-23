/***********************************************
 * Filename		: UserDao.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.innovaee.eorder.module.entity.User;

/**
 * @Title: UserDao
 * @Description: 用户数据访问对象
 * @author coderdream@gmail.com
 * @version V1.0
 */
public class UserDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return User.class;
	}

	/**
	 * 通过用户名查找用户
	 * 
	 * @param username
	 *            用户名
	 * @return 用户
	 */
	@SuppressWarnings("unchecked")
	public User findUserByUserName(String username) {
		List<User> list = (List<User>) super.getHibernateTemplate().find(
				"FROM User u WHERE u.username=?", username);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 通过手机号码查找用户
	 * 
	 * @param cellphone
	 *            手机号码
	 * @return 用户
	 */
	@SuppressWarnings("unchecked")
	public User findUserByCellphone(String cellphone) {
		List<User> list = (List<User>) super.getHibernateTemplate().find(
				"FROM User u WHERE u.cellphone=?", cellphone);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 通过用户ID查找用户
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户
	 */
	public User loadUser(Integer userId) {
		return (User) get(userId);
	}

	/**
	 * 查找所有用户
	 * 
	 * @return 用户列表
	 */
	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return (List<User>) super.getHibernateTemplate().find("FROM User");
	}

	/**
	 * 通过用户名密码查找用户
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 用户
	 */
	public User getUserByPassword(String username, String password) {
		User user = (User) get(username);

		if (null == user) {
			return null;
		}

		return (0 == password.compareTo(user.getPassword())) ? user : null;
	}

	/**
	 * 保存用户
	 * 
	 * @param user
	 *            用户信息
	 * @return 被保存的用户
	 */
	public User saveUser(User user) {
		Timestamp createAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		user.setCreateAt(createAt);
		return (User) save(user);
	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 *            待更新的用户信息
	 */
	public void updateUser(User user) {
		Timestamp updateAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		user.setUpdateAt(updateAt);
		update(user);
	}

	/**
	 * 移除用户
	 * 
	 * @param user
	 *            待移除的用户信息
	 */
	public void removeUser(User user) {
		super.getHibernateTemplate().delete(user);
	}
}
