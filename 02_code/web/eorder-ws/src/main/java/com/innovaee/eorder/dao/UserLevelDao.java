package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.bean.UserLevel;

public interface UserLevelDao {

	public UserLevel getUserLevelById(String id);

	public boolean deleteUserLevelById(String id);

	public boolean createUserLevel(UserLevel userLevel);

	public boolean updateUserLevel(UserLevel userLevel);

	public List<UserLevel> getAllUserLeveles();

	public UserLevel getUserLevelByUserId(String userId);
}