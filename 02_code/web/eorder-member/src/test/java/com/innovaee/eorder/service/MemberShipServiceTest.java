/***********************************************
 * Filename        : MemberShipServiceTest.java
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created         : 03/24/2015
 ************************************************/

package com.innovaee.eorder.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.innovaee.eorder.entity.User;
import com.innovaee.eorder.entity.UserLevel;
import com.innovaee.eorder.exception.DuplicateNameException;
import com.innovaee.eorder.exception.InvalidPageSizeException;
import com.innovaee.eorder.exception.MeberShipAlreadyExistException;
import com.innovaee.eorder.exception.PageIndexOutOfBoundExcpeiton;
import com.innovaee.eorder.exception.UserLevelNotFoundException;
import com.innovaee.eorder.exception.UserNotFoundException;
import com.innovaee.eorder.vo.UserLevelVO;

import org.junit.Test;

import java.util.List;

import javax.annotation.Resource;

/**
 * @Title: MemberShipServiceTest
 * @Description: MemberShipService 单元测试类
 * 
 * @version V1.0
 */
public class MemberShipServiceTest extends BaseSpringTestCase {
    @Resource
    private MemberShipServcie memberShipService;
    @Resource
    private UserService userService;

    /**
     * 单元测试-获取所有会员等级
     */
    @Test
    public void getAllUserLevelsTest() {
        assertEquals(4, memberShipService.getAllUserLevels().size());
    }

    /**
     * 单元测试-使用错误的id获取会员等级
     */
    @Test
    public void getUserLevelbyInvalidIdTest() {
        try {
            memberShipService.getUserLevelById(-1L);
        } catch (Exception exception) {
            assertEquals(UserLevelNotFoundException.class, exception.getClass());
        }
    }

    /**
     * 单元测试-使用正确的id获取会员等级
     * 
     * @throws UserLevelNotFoundException
     */
    @Test
    public void getUserLevelByValidIdTest() throws UserLevelNotFoundException {
        UserLevel userLevel = memberShipService.getUserLevelById(1L);

        assertNotNull(userLevel);
    }

    /**
     * 单元测试-使用非法名称获取会员等级
     * 
     * @throws UserLevelNotFoundException
     */
    @Test
    public void getUserLevelByInvalidNameTest() {
        String name = "未知会员";

        try {
            memberShipService.getUserLevelByName(name);
        } catch (Exception exception) {
            assertEquals(UserLevelNotFoundException.class, exception.getClass());
        }
    }

    /**
     * 单元测试-使用非法名称获取会员等级
     * 
     * @throws UserLevelNotFoundException
     */
    @Test
    public void getUserLevelByValidNameTest() throws UserLevelNotFoundException {
        String name = "钻石会员";

        UserLevel userLevel = memberShipService.getUserLevelByName(name);

        assertNotNull(userLevel);
    }

    /**
     * 单元测试-使用非法名称获取会员等级
     * 
     * @throws UserLevelNotFoundException
     */
    @Test
    public void getUserLevelPageCountWithInvalidPageSizeTest() {
        int pageSize = -1;

        try {
            memberShipService.getUserLevePageCount(pageSize);
        } catch (Exception exception) {
            assertEquals(InvalidPageSizeException.class, exception.getClass());
        }
    }

    /**
     * 单元测试-添加会员等级
     */
    @Test
    public void addUserLevelTest() {

        String name = "白金会员";

        UserLevelVO userLevelVO = new UserLevelVO();
        userLevelVO.setDiscount(9.0f);
        userLevelVO.setLevelScore(1000);
        userLevelVO.setName(name);
        userLevelVO.setStatus(true);

        try {
            memberShipService.addUserLevel(userLevelVO);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), DuplicateNameException.class);
        }

        userLevelVO.setName(name + "_test");
        try {
            memberShipService.addUserLevel(userLevelVO);
        } catch (Exception exception) {
            assert (false);
        }

    }

    /**
     * 单元测试-使用错误的ID更新会员等级
     */
    @Test
    public void updateUserLevelByInvalidIdTest() {
        UserLevel userLevel = null;
        try {
            userLevel = memberShipService.getUserLevelById(-1L);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), UserLevelNotFoundException.class);
        }

        assertNull(userLevel);
    }

    /**
     * 单元测试-使用重名会员等级更新会员等级
     */
    @Test
    public void updateUserLevelByDuplicateNameTest() {
        UserLevel userLevel = null;
        UserLevelVO userLevelVO = new UserLevelVO();
        try {
            String name = "白金会员";
            userLevel = memberShipService.getUserLevelById(1L);

            userLevelVO.setId(userLevel.getId());
            userLevelVO.setDiscount(userLevel.getDiscount());
            userLevelVO.setLevelScore(userLevel.getLevelScore());
            userLevelVO.setName(name);
            userLevelVO.setStatus(true);

            userLevel = memberShipService.updateUserLevel(userLevelVO);

        } catch (Exception exception) {
            assertEquals(exception.getClass(), DuplicateNameException.class);
        }

        assertFalse(userLevel.getLevelName().equals(userLevelVO.getName()));
    }

    /**
     * 单元测试-更新会员等级
     * 
     * @throws UserLevelNotFoundException
     * @throws DuplicateNameException
     */
    @Test
    public void updateUserLevelTest() throws UserLevelNotFoundException,
            DuplicateNameException {
        UserLevel userLevel = null;
        UserLevelVO userLevelVO = new UserLevelVO();

        userLevel = memberShipService.getUserLevelById(1L);

        userLevelVO.setId(userLevel.getId());
        userLevelVO.setDiscount(7.5f);
        userLevelVO.setLevelScore(userLevel.getLevelScore());
        userLevelVO.setName(userLevel.getLevelName());
        userLevelVO.setStatus(true);

        userLevel = memberShipService.updateUserLevel(userLevelVO);

        assertTrue(userLevel.getLevelName().equals(userLevelVO.getName()));
    }

    /**
     * 单元测试-删除会员等级
     * 
     * @throws UserLevelNotFoundException
     */
    @Test
    public void deleteUserLevelTest() throws UserLevelNotFoundException {
        memberShipService.deleteUserLevel(1L);
    }

    /**
     * 单元测试-使用非法名称获取会员等级
     * 
     * @throws InvalidPageSizeException
     * 
     * @throws UserLevelNotFoundException
     */
    @Test
    public void getUserLevelPageCountWithValidPageSizeTest()
            throws InvalidPageSizeException {
        int pageSize = 5;

        assertEquals(1, memberShipService.getUserLevePageCount(pageSize));
    }

    /**
     * 单元测试-会员等级分页测试
     * 
     * @throws InvalidPageSizeException
     * @throws PageIndexOutOfBoundExcpeiton
     * 
     */
    @Test
    public void getUserLevelByPageTest() throws PageIndexOutOfBoundExcpeiton,
            InvalidPageSizeException {
        List<UserLevel> userLevels = memberShipService
                .getUserLevelsByPage(1, 6);

        assertEquals(5, userLevels.size());
    }

    /**
     * 单元测试-给用户添加会员信息
     * 
     * @throws MeberShipAlreadyExistException
     * @throws UserLevelNotFoundException
     * @throws UserNotFoundException
     * 
     */
    @Test
    public void addMemberShipToUserTest() throws UserNotFoundException,
            UserLevelNotFoundException, MeberShipAlreadyExistException {

        try {
            memberShipService.addMemberShipToUser(-1L, 1L, true);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), UserLevelNotFoundException.class);
        }

        try {
            memberShipService.addMemberShipToUser(1L, -1L, true);
        } catch (Exception exception) {
            assertEquals(exception.getClass(), UserNotFoundException.class);
        }

        memberShipService.addMemberShipToUser(1L, 1L, true);

        try {
            memberShipService.addMemberShipToUser(1L, 1L, false);
        } catch (Exception exception) {
            assertEquals(exception.getClass(),
                    MeberShipAlreadyExistException.class);
        }
    }

    @Test
    public void getUsersByUserLevelTest() throws UserLevelNotFoundException,
            InvalidPageSizeException, PageIndexOutOfBoundExcpeiton {
        List<User> users = memberShipService.getUsersbyUserLevel(1L, 1, 5);
        
        assertNotNull(users);
        assertEquals(1, users.size());
        
        assertNotNull(users.get(0).getCellphone());
    }
}
