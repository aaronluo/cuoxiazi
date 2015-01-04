/***********************************************
 * Filename		: UserLevelDao.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao;

import com.innovaee.eorder.bean.UserLevel;

/**
 * @Title: UserLevelDao
 * @Description: 用户等级数据访问对象接口
 * 
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

}