/***********************************************
 * Filename		: UserLevelDao.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao;

import java.util.List;

import com.innovaee.eorder.bean.UserLevel;

/**
 * @Title: UserLevelDao
 * @Description: 用户等级数据访问对象接口
 * @author coderdream@gmail.com
 * @version V1.0
 */
public interface UserLevelDao {

	/**
	 * 根据用户等级ID查找用户等级
	 * 
	 * @param id
	 *            用户等级ID
	 * @return 用户等级实体
	 */
	public UserLevel getUserLevelById(String id);

	/**
	 * 根据用户等级ID删除用户等级
	 * 
	 * @param id
	 *            用户等级实体
	 * @return 用户等级是否删除成功
	 */
	public boolean deleteUserLevelById(String id);

	/**
	 * 创建用户等级
	 * 
	 * @param category
	 *            待创建的用户等级
	 * @return 用户等级是否创建成功
	 */
	public boolean createUserLevel(UserLevel userLevel);

	/**
	 * 更新用户等级
	 * 
	 * @param category
	 *            待更新的用户等级
	 * @return 用户等级是否更新成功
	 */
	public boolean updateUserLevel(UserLevel userLevel);

	/**
	 * 获取所有用户等级
	 * 
	 * @return 所有用户等级列表
	 */
	public List<UserLevel> getAllUserLeveles();

	/**
	 * 根据用户ID得到用户等级
	 * 
	 * @param userId
	 *            用户ID
	 * @return 用户等级
	 */
	public UserLevel getUserLevelByUserId(String userId);

}