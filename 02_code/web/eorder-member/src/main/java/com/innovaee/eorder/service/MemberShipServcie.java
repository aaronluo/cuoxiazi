/***********************************************
 * Filename       : MemberShipServcie.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/19/2015
 ************************************************/

package com.innovaee.eorder.service;

import com.innovaee.eorder.entity.MemberShip;
import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.MeberShipAlreadyExistException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.exception.UpdateUserLevelException;
import com.innovaee.eorder.exception.UserLevelNotFoundException;
import com.innovaee.eorder.exception.UserNotFoundException;
import com.innovaee.eorder.vo.UserLevelVO;

import java.util.List;

/**
 * @Title: MemberShipServcie
 * @Description: 用户会员管理服务接口
 * 
 * @version V1.0
 */
public interface MemberShipServcie {

    /**
     * 获取所有会员等级信息
     * 
     * @return 会员等级列表
     */
    public List<UserLevel> getAllUserLevels();

    /**
     * 添加新会员等级
     * 
     * @param userLevelVO
     *            - 会员等级信息
     * @return 新会员等级实体
     * @throws DuplicateNameException
     *             会员等级命名重复异常
     */
    public UserLevel addUserLevel(UserLevelVO userLevelVO)
            throws DuplicateNameException;

    /**
     * 更新会员等级信息
     * 
     * @param userLevelVO
     *            - 新会员等级信息
     * @return 更新后的会员等级实体
     * @throws DuplicateNameException
     *             会员等级命名重复异常
     * @throws UserLevelNotFoundException
     *             会员等级不存在异常
     */
    public UserLevel updateUserLevel(UserLevelVO userLevelVO)
            throws DuplicateNameException, UserLevelNotFoundException;

    /**
     * 删除会员等级
     * 
     * @param userLevelId
     *            待删除会员等级id
     * @return
     * @throws UserLevelNotFoundException
     */
    public void deleteUserLevel(Long userLevelId)
            throws UserLevelNotFoundException;

    /**
     * 根据指定名字获取会员等级
     * 
     * @param userLevelName
     *            会员等级名字
     * @return 会员等级实体
     * @throws UserLevelNotFoundException
     *             会员等级未找到异常
     */
    public UserLevel getUserLevelByName(String userLevelName)
            throws UserLevelNotFoundException;

    /**
     * 根据指定的ID获取会员等级
     * 
     * @param userLevleId
     *            会员等级ID
     * @return 会员等级实体
     * @throws UserLevelNotFoundException
     *             会员等级未找到异常
     */
    public UserLevel getUserLevelById(Long userLevelId)
            throws UserLevelNotFoundException;

    /**
     * 获取会员等级分页总数
     * 
     * @param pageSize
     *            分页大小
     * @return 分页总数
     * @throws InvalidPageSizeException
     *             非法的分页大小异常
     */
    public int getUserLevelPageCount(int pageSize)
            throws InvalidPageSizeException;

    /**
     * 获取会员等级分页列表
     * 
     * @param curPage
     *            当前页数
     * @param pageSize
     *            分页大小
     * @return 当前分页内的会员等级信息
     * @throws PageIndexOutOfBoundExcpeiton
     *             非法当前页异常
     * @throws InvalidPageSizeException
     *             非法的分页大小异常
     */
    public List<UserLevel> getUserLevelsByPage(int curPage, int pageSize)
            throws PageIndexOutOfBoundExcpeiton, InvalidPageSizeException;

    /**
     * 给指定用户添加指定的会员信息
     * 
     * @param userLevelId
     * @param userId
     * @return
     * @throws UserNotFoundException
     * @throws UserLevelNotFoundException
     * @throws MeberShipAlreadyExistException
     */
    public MemberShip addMemberShipToUser(Long userLevelId, Long userId,
            boolean forceLinkage) throws UserNotFoundException,
            UserLevelNotFoundException, MeberShipAlreadyExistException;

    /**
     * 删除指定用户的会员信息
     * 
     * @param userId
     *            用户ID
     * @throws UserNotFoundException
     *             用户没有找到异常
     */
    public void deleteMemberShipOfUser(Long userId)
            throws UserNotFoundException;

    /**
     * 对指定的用户会员等级进行升级或者降级操作
     * 
     * @param userId
     *            用户ID
     * @param downOrUp
     *            升级或降级标志位 1-升级；0-降级
     * @return 返回更新后的用户会员信息
     * @throws UserNotFoundException
     *             用户未找到异常
     * @throws UpdateUserLevelException
     *             用户等级操作失败异常
     */
    public MemberShip updateUserLevelOfUser(Long userId, int downOrUp)
            throws UserNotFoundException, UpdateUserLevelException;

    /**
     * 获取指定会员等级的用户分页数据
     * 
     * @param userLevelId
     *            会员等级ID
     * @param curPage
     *            当前页数
     * @param pageSize
     *            分页大小
     * @return 属于指定会员等级的用户分页集合
     * @throws UserLevelNotFoundException
     *             会员等级不存在异常
     * @throws InvalidPageSizeException
     *             非法的分页大小异常
     * @throws PageIndexOutOfBoundExcpeiton
     *             当前页越界异常
     */
    public List<User> getUsersbyUserLevel(Long userLevelId, int curPage,
            int pageSize) throws UserLevelNotFoundException,
            InvalidPageSizeException, PageIndexOutOfBoundExcpeiton;

    /**
     * 获取指定会员等级的用户分页总数
     * 
     * @param userLevelId
     *            会员等级ID
     * @param pageSize
     *            分页大小
     * @return 用户分页总数
     * @throws UserLevelNotFoundException
     *             会员等级不存在异常
     * @throws InvalidPageSizeException
     *             非法的分页大小异常
     */
    public int getUsersByUserLevelPageCount(Long userLevelId, int pageSize)
            throws UserLevelNotFoundException, InvalidPageSizeException;

    /**
     * 获取指定会员等级的用户总数
     * 
     * @param userLevleId
     *            会员等级ID
     * @return 用户总数
     * @throws UserLevelNotFoundException
     *             会员等级不存在异常
     */
    public int getUsersByUserLevelCount(Long userLevleId)
            throws UserLevelNotFoundException;
    
    public UserLevel getNexLevel(Long userLevelId) throws UserLevelNotFoundException;
    
    public UserLevel getPreLevel(Long userLevelId) throws UserLevelNotFoundException;
    
    public UserLevel getNearestLevel(final int score);

    public void updateUserMemberShip(Long id, int newScore) throws UserNotFoundException;
}
