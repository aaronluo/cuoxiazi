/***********************************************
 * Filename		: UserLevelDao.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.innovaee.eorder.module.entity.UserLevel;

/**
 * @Title: UserLevelDao
 * @Description: 用户等级数据访问对象
 * @author coderdream@gmail.com
 * @version V1.0
 */
public class UserLevelDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return UserLevel.class;
	}

	/**
	 * 通过用户等级名称查找用户等级
	 * 
	 * @param userLevelname
	 *            用户等级名称
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public UserLevel findUserLevelsByUserLevelName(String userLevelname) {
		List<UserLevel> list = (List<UserLevel>) super
				.getHibernateTemplate()
				.find("FROM UserLevel u WHERE u.userLevelname=?", userLevelname);
		if (null != list && 0 < list.size()) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查找所有的用户等级
	 * 
	 * @return 用户等级列表
	 */
	@SuppressWarnings("unchecked")
	public List<UserLevel> findAllUserLevels() {
		return (List<UserLevel>) super.getHibernateTemplate().find(
				"FROM UserLevel");
	}

	/**
	 * 通过等级ID查找用户等级
	 * 
	 * @param levelId
	 *            等级ID
	 * @return 用户等级
	 */
	public UserLevel loadUserLevel(Integer levelId) {
		return (UserLevel) get(levelId);
	}

	/**
	 * 保存用户等级信息
	 * 
	 * @param userLevel
	 *            待保存的用户等级信息
	 * @return 待保存的用户等级
	 */
	public UserLevel saveUserLevel(UserLevel userLevel) {
		return (UserLevel) save(userLevel);
	}

	/**
	 * 更新用户等级信息
	 * 
	 * @param userLevel
	 *            待更新的用户等级信息
	 */
	public void updateUserLevel(UserLevel userLevel) {
		Timestamp updateAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		userLevel.setUpdateAt(updateAt);
		update(userLevel);
	}

	/**
	 * 移除用户等级信息
	 * 
	 * @param userLevel
	 *            待移除的用户等级信息
	 */
	public void removeUserLevel(UserLevel userLevel) {
		super.getHibernateTemplate().delete(userLevel);
	}
}
