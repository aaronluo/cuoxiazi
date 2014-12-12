package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.bean.User;

public interface UserDao {

	public User getUserById(String id);

	public boolean deleteUserById(String id);

	public boolean createUser(User user);

	public boolean updateUser(User user);

	public List<User> getAllUseres();

	public User getUserByCellphone(String cellphone);
}