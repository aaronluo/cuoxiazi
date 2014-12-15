package com.innovaee.eorder.module.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.innovaee.eorder.module.entity.User;

public class UserDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return User.class;
	}

	@SuppressWarnings("unchecked")
	public User findUserByUserName(String username) {
		List<User> list = (List<User>) super.getHibernateTemplate().find(
				"FROM User u WHERE u.username=?", username);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public User findUserByCellphone(String cellphone) {
		List<User> list = (List<User>) super.getHibernateTemplate().find(
				"FROM User u WHERE u.cellphone=?", cellphone);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}
	
	public User loadUser(Integer userId) {
		return (User) get(userId);
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return (List<User>) super.getHibernateTemplate().find("FROM User");
	}

	public User getUserByPassword(String username, String password) {
		User user = (User) get(username);

		if (null == user) {
			return null;
		}

		return (0 == password.compareTo(user.getPassword())) ? user : null;
	}

	public User saveUser(User user) {
		Timestamp createAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		user.setCreateAt(createAt);
		return (User) save(user);
	}

	public void updateUser(User user) {
		Timestamp updateAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		user.setUpdateAt(updateAt);
		update(user);
	}

	public void removeUser(User user) {
		super.getHibernateTemplate().delete(user);
	}
}
