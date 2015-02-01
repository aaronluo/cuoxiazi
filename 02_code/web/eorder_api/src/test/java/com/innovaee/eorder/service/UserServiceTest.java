package com.innovaee.eorder.service;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.innovaee.eorder.model.User;

public class UserServiceTest extends BaseSpringTestCase {

    /** 功能服务类对象 */
    @Autowired
    private UserService userService;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUserByCellphone() {
        String cellphone = "13912345671";
        User user = userService.getUserByCellphone(cellphone);
        LOGGER.debug("user: " + user);
    }

}