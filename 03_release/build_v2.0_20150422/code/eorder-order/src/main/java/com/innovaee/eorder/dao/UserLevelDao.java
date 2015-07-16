/***********************************************
 * Filename        : UserLevelDao.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/
package com.innovaee.eorder.dao;

import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;

import java.util.List;

/**
 * @Title: UserLevelDao
 * @Description: 用户等级数据访问对象接口
 * 
 * @version V1.0
 */
public interface UserLevelDao extends BaseDao<UserLevel> {

    /**
     * 根据指定名称查找用户等级信息
     * @param name
     * @return
     */
    UserLevel getUserLevelByName(String name);

    /**
     * 获取当前用户等级的下一个等级
     * @param id
     * @return
     */
    UserLevel getNextLevel(Long id);
    /**
     * 获取当前用户等级的上一个等级
     * @param id
     * @return
     */
    UserLevel getPreLevel(Long id);

    /**
     * 获取拥有指定会员等级的会员用户分页数据
     * @param userLevelId 会员等级ID
     * @param curPage 当前页
     * @param pageSize 分页大小
     * @return
     */
    List<User> getUsers(Long userLevelId, int curPage, int pageSize);
    
    UserLevel getNearestLevel(final int score);

}