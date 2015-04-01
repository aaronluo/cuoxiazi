/***********************************************
 * Filename       : MeberShipServiceImpl.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/23/2015
 ************************************************/

package com.innovaee.eorder.service.impl;

import com.innovaee.eorder.dao.MemberShipDao;
import com.innovaee.eorder.dao.UserDao;
import com.innovaee.eorder.dao.UserLevelDao;
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
import com.innovaee.eorder.service.MemberShipServcie;
import com.innovaee.eorder.utils.Constants;
import com.innovaee.eorder.utils.MessageUtil;
import com.innovaee.eorder.vo.UserLevelVO;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Title: MeberShipServiceImpl
 * @Description: 用户会员管理服务实现类
 * 
 * @version V1.0
 */
public class MemberShipServiceImpl implements MemberShipServcie {
    /** 会员等级数据访问对象 */
    private UserLevelDao userLevelDao;
    /** 会员信息数据访问对象 */
    private MemberShipDao memberShipDao;
    /** 用户数据访问对象 */
    private UserDao userDao;

    /**
     * 获取所有会员等级信息
     * 
     * @return 会员等级列表
     */
    public List<UserLevel> getAllUserLevels() {
        List<UserLevel> levels = userLevelDao.loadAll();

        for (UserLevel level : levels) {
            if (level.getLevelName().equals(Constants.DEFAULT_USR_LEVEL)) {
                levels.remove(level);
                break;
            }
        }

        return levels;
    }

    /**
     * 添加新会员等级
     * 
     * @param userLevelVO
     *            - 会员等级信息
     * @return 新会员等级实体
     * @throws DuplicateNameException
     *             会员等级命名重复异常
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public UserLevel addUserLevel(UserLevelVO userLevelVO)
            throws DuplicateNameException {
        UserLevel userLevel = userLevelDao.getUserLevelByName(userLevelVO
                .getName());
        // 1. 检查是否有同名的用户等级存在
        if (null != userLevel) {
            throw new DuplicateNameException(userLevelVO.getName());
        } else {
            userLevel = new UserLevel();
            userLevel.setLevelName(userLevelVO.getName());
            userLevel.setDiscount(userLevelVO.getDiscount());
            userLevel.setLevelScore(userLevelVO.getLevelScore());
            userLevel.setLevelStatus(true);
            userLevel.setCreateDate(new Date());

            userLevel.setId(userLevelDao.save(userLevel));
        }

        return userLevel;
    }

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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public UserLevel updateUserLevel(UserLevelVO userLevelVO)
            throws DuplicateNameException, UserLevelNotFoundException {
        // 1. 检查要更新的用户等级是否存在
        UserLevel userLevel = userLevelDao.get(userLevelVO.getId());

        if (null == userLevel) {
            throw new UserLevelNotFoundException(MessageUtil.getMessage(
                    "user_level_id", "" + userLevelVO.getId()));
        } else {
            // 2. 检查是否有同名的userLevel存在
            userLevel = userLevelDao.getUserLevelByName(userLevelVO.getName());
            if (userLevel != null && userLevel.getId() != userLevelVO.getId()) {
                throw new DuplicateNameException(userLevelVO.getName());
            } else {
                userLevel = userLevelDao.get(userLevelVO.getId());

                userLevel.setUpdateDate(new Date());
                userLevel.setDiscount(userLevelVO.getDiscount());
                userLevel.setLevelName(userLevelVO.getName());
                userLevel.setLevelScore(userLevelVO.getLevelScore());

                userLevelDao.update(userLevel);
            }
        }

        return userLevel;
    }

    /**
     * 删除会员等级
     * 
     * @param userLevelId
     *            待删除会员等级id
     * @return
     * @throws UserLevelNotFoundException
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void deleteUserLevel(Long userLevelId)
            throws UserLevelNotFoundException {
        // 会员等级信息不会真正的删除，而是将levelStatus变为flase
        UserLevel userLevel = userLevelDao.get(userLevelId);
        if (null == userLevel) {
            throw new UserLevelNotFoundException(MessageUtil.getMessage(
                    "user_level_id", "" + userLevelId));
        } else {
            userLevel.setLevelStatus(false);
            userLevelDao.update(userLevel);
        }
    }

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
            throws UserLevelNotFoundException {
        UserLevel userLevel = userLevelDao.getUserLevelByName(userLevelName);
        if (null == userLevel) {
            throw new UserLevelNotFoundException(MessageUtil.getMessage(
                    "user_level_name", userLevelName));
        }

        return userLevel;
    }

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
            throws UserLevelNotFoundException {
        UserLevel userLevel = userLevelDao.get(userLevelId);
        if (null == userLevel) {
            throw new UserLevelNotFoundException(MessageUtil.getMessage(
                    "user_level_id", "" + userLevelId));
        }

        return userLevel;
    }

    /**
     * 获取会员等级分页总数
     * 
     * @param pageSize
     *            分页大小
     * @return 分页总数
     * @throws InvalidPageSizeException
     *             非法的分页大小异常
     */
    public int getUserLevePageCount(int pageSize)
            throws InvalidPageSizeException {
        if (pageSize <= 0) {
            throw new InvalidPageSizeException(pageSize);
        }

        int userLevelCount = userLevelDao.loadAll().size();

        return userLevelCount % pageSize == 0 ? userLevelCount / pageSize
                : userLevelCount / pageSize + 1;

    }

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
            throws PageIndexOutOfBoundExcpeiton, InvalidPageSizeException {
        int totalPage = this.getUserLevePageCount(pageSize);
        List<UserLevel> userLevels = new ArrayList<UserLevel>();
        if (curPage < 1 || curPage > totalPage) {
            throw new PageIndexOutOfBoundExcpeiton(totalPage, curPage);
        }

        int startIndex = (curPage - 1) * pageSize;
        userLevels = userLevelDao.getPage(startIndex, pageSize,
                "FROM UserLevel AS userLevel WHERE userLevel.levelStatus=true");

        return userLevels;
    }

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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public MemberShip addMemberShipToUser(Long userLevelId, Long userId,
            boolean forceLinkage) throws UserNotFoundException,
            UserLevelNotFoundException, MeberShipAlreadyExistException {
        // 1. 检查要添加会员信息的用户是否存在
        User user = userDao.get(userId);
        if (null == user) {
            throw new UserNotFoundException(MessageUtil.getMessage("user_id",
                    "" + userId));
        }
        // 2. 检查要添加到会员等级是否存在
        UserLevel userLevel = userLevelDao.get(userLevelId);
        if (null == userLevel) {
            throw new UserLevelNotFoundException(MessageUtil.getMessage(
                    "user_level_id", "" + userLevelId));
        }
        // 3. 如果用户已经有会员信息存在
        if (user.getMemberShip() != null) {
            // 3.1 如果指定强行新的会员信息，则删除当前的会员信息
            if (forceLinkage) {
                deleteMemberShipOfUser(userId);
            } else {
                // 3.2 如果不强行指定添加新会员信息，则抛出异常
                throw new MeberShipAlreadyExistException(user.getMemberShip()
                        .getId());
            }
        }
        // 4. 创建新的会员信息复制给用户
        MemberShip memberShip = new MemberShip();

        memberShip.setCreateDate(new Date());
        memberShip.setCurrentScore(0);
        memberShip.setLevel(userLevel);
        memberShip.setUser(user);
        memberShip.setMemberId(user.getCellphone());

        memberShip.setId(memberShipDao.save(memberShip));

        return memberShip;
    }

    /**
     * 删除指定用户的会员信息
     * 
     * @param userId
     *            用户ID
     * @throws UserNotFoundException
     *             用户没有找到异常
     */
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public void deleteMemberShipOfUser(Long userId)
            throws UserNotFoundException {
        User user = userDao.get(userId);

        if (null == user) {
            throw new UserNotFoundException(MessageUtil.getMessage("user_id",
                    "" + userId));
        }

        if (user.getMemberShip() != null) {
            memberShipDao.delete(user.getMemberShip());
        }
    }

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
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
    public MemberShip updateUserLevelOfUser(Long userId, int downOrUp)
            throws UserNotFoundException, UpdateUserLevelException {
        User user = userDao.get(userId);

        if (null == user) {
            throw new UserNotFoundException(MessageUtil.getMessage("user_id",
                    "" + userId));
        }

        MemberShip memberShip = user.getMemberShip();

        if (null == memberShip) {
            throw new UpdateUserLevelException(
                    MessageUtil.getMessage("no_member_ship"));
        }

        UserLevel userLevel = null;

        if (1 == downOrUp) {
            userLevel = userLevelDao
                    .getNextLevel(memberShip.getLevel().getId());
        } else {
            userLevel = userLevelDao
                    .getLastLevel(memberShip.getLevel().getId());
        }
        // 不能升级/降级的时候抛出异常
        if (null == userLevel) {
            throw new UpdateUserLevelException(MessageUtil.getMessage(
                    "up_down_user_level", "" + downOrUp, memberShip.getLevel()
                            .getLevelName()));
        }

        memberShip.setLevel(userLevel);

        memberShipDao.update(memberShip);

        return memberShip;
    }

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
            InvalidPageSizeException, PageIndexOutOfBoundExcpeiton {

        if (pageSize <= 0) {
            throw new InvalidPageSizeException(pageSize);
        }

        if (null == userLevelDao.get(userLevelId)) {
            throw new UserLevelNotFoundException(MessageUtil.getMessage(
                    "user_level_id", "" + userLevelId));
        }

        final int totalPage = this.getUsersByUserLevelPageCount(userLevelId,
                pageSize);

        if (curPage < 1 || curPage > totalPage) {
            throw new PageIndexOutOfBoundExcpeiton(totalPage, curPage);
        }

        return userLevelDao.getUsers(userLevelId, curPage, pageSize);
    }

    /**
     * 获取指定会员等级的用户分页总数
     * 
     * @param userLevelId
     *            会员等级ID
     * @param pageSize
     *            分页大小
     * @return 用户总数
     * @throws UserLevelNotFoundException
     *             会员等级不存在异常
     * @throws InvalidPageSizeException
     *             非法的分页大小异常
     */
    public int getUsersByUserLevelPageCount(Long userLevelId, int pageSize)
            throws UserLevelNotFoundException, InvalidPageSizeException {
        int count = getUsersByUserLevelCount(userLevelId);

        return count % pageSize == 0 ? count / pageSize : count / pageSize + 1;
    }

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
            throws UserLevelNotFoundException {
        if (userLevelDao.get(userLevleId) == null) {
            throw new UserLevelNotFoundException(MessageUtil.getMessage(
                    "user_level_id", "" + userLevleId));
        }

        return userLevelDao.getUsers(userLevleId, 1, Integer.MAX_VALUE).size();
    }

    public UserLevelDao getUserLevelDao() {
        return userLevelDao;
    }

    public void setUserLevelDao(UserLevelDao userLevelDao) {
        this.userLevelDao = userLevelDao;
    }

    public MemberShipDao getMemberShipDao() {
        return memberShipDao;
    }

    public void setMemberShipDao(MemberShipDao memberShipDao) {
        this.memberShipDao = memberShipDao;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
}
