package com.innovaee.eorder.module.dao;

import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.entity.User;

public class UserDao extends BaseDao {

	@Resource
	private UserDao userDao;

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return User.class;
	}

	@SuppressWarnings("unchecked")
	public User findUsersByUserName(String userName) {
		List<User> list = (List<User>) super.getHibernateTemplate().find("FROM User f WHERE f.userName=?", userName);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<User> findAllUsers() {
		return (List<User>) super.getHibernateTemplate().find("FROM User");
	}

	public User getUserByPassword(String username, String password) {
		User user = (User) userDao.get(username);

		if (null == user) {
			return null;
		}

		return (0 == password.compareTo(user.getUserPassword())) ? user : null;
	}

	public User saveUser(User user) {
		return (User) save(user);
	}

	public void updateUser(User user) {
		update(user);
	}

	public void removeUser(User user) {
		super.getHibernateTemplate().delete(user);
	}
}
