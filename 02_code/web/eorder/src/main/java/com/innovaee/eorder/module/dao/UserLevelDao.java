package com.innovaee.eorder.module.dao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import com.innovaee.eorder.module.entity.UserLevel;

public class UserLevelDao extends BaseDao {

	@SuppressWarnings("rawtypes")
	@Override
	protected Class getEntityClass() {
		return UserLevel.class;
	}

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

	@SuppressWarnings("unchecked")
	public List<UserLevel> findAllUserLevels() {
		return (List<UserLevel>) super.getHibernateTemplate().find(
				"FROM UserLevel");
	}
	
	public UserLevel loadUserLevel(Integer levelId) {
		return (UserLevel) get(levelId);
	}


	public UserLevel saveUserLevel(UserLevel userLevel) {
		return (UserLevel) save(userLevel);
	}

	public void updateUserLevel(UserLevel userLevel) {
		Timestamp updateAt = Timestamp.valueOf(new SimpleDateFormat(
				"yyyy-MM-dd hh:mm:ss.SSS").format(Calendar.getInstance()
				.getTime()));
		userLevel.setUpdateAt(updateAt);
		update(userLevel);
	}

	public void removeUserLevel(UserLevel userLevel) {
		super.getHibernateTemplate().delete(userLevel);
	}
}
