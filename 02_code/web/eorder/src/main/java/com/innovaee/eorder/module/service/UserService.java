package com.innovaee.eorder.module.service;

import java.util.List;

import javax.annotation.Resource;

import com.innovaee.eorder.module.dao.UserDao;
import com.innovaee.eorder.module.entity.User;

public class UserService extends BaseService {

	@Resource
	private UserDao userDao;

	public List<User> findAllUsers() {
		return (List<User>) userDao.findAllUsers();
	}

	public User getUserByPassword(String loginId, String password) {
		return userDao.getUserByPassword(loginId, password);
	}

	public User findUsersByUserName(String userName) {
		return (User) userDao.findUsersByUserName(userName);
	}

	public User saveUser(User user) {
		return userDao.saveUser(user);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	public void removeUser(User user) {
		userDao.removeUser(user);
	}

	public void removeUser(String userName) {
		userDao.removeUser(new User(userName));
	}

	public void removeUsers(String[] userName) {
		int length = userName.length;
		for (int i = 0; i < length; i++) {
			userDao.removeUser(new User(userName[i]));
		}

	}
}