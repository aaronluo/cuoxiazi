package com.innovaee.eorder.module.service;

import com.innovaee.eorder.module.entity.Role;
import com.innovaee.eorder.module.entity.User;
import com.innovaee.eorder.module.entity.UserRole;
import com.innovaee.eorder.module.utils.StringUtil;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: UserRoleServiceTest
 * @Description: 用户角色服务测试类
 * @version V1.0
 */
public class UserRoleServiceTest extends BaseSpringTestCase {

    /** 用户角色服务类对象 */
    @Autowired
    private UserRoleService userRoleService;

    /** 用户服务类对象 */
    @Autowired
    private UserService userService;

    /** 用户名 */
    private String username = "abcba";

    /** 密码 */
    private String password = "12345";

    /** 手机号码 */
    private String cellphone = "13888888888";

    /** 等级ID */
    private Integer levelId = 1;

    /** 用户状态 */
    private Boolean userStatus = true;

    /** 角色服务类对象 */
    @Autowired
    private RoleService roleService;

    /** 角色名称 */
    private String roleName = "RoleName1";

    /** 角色描述 */
    private String roleDesc = "RoleDesc1";

    /** 角色状态 */
    private Boolean roleStatus = true;

    /**
     * 查找所有用户角色
     */
    @Test
    public void getAllUserRoles() {
        List<UserRole> allUserRoles = userRoleService.findAllUserRoles();
        Assert.assertNotNull(allUserRoles);
        for (UserRole userRole : allUserRoles) {
            LOGGER.debug(userRole);
        }
    }

    /**
     * 先增加，再查找，再删除，再查找
     */
    @Test
    public void operateUserRole() {
        User user = new User(username, password, cellphone, levelId, userStatus);

        // 1. 保存
        User userNew = userService.saveUser(user);
        // 得到新增后的ID
        Integer newUserId = userNew.getUserId();

        // 先新增一个对象
        Role role = new Role(roleName, roleDesc, roleStatus);
        // 1. 保存
        Role roleNew = roleService.saveRole(role);
        // 得到新增后的ID
        Integer newRoleId = roleNew.getRoleId();
        List<Integer> roleIdList = new ArrayList<Integer>();
        roleIdList.add(newRoleId);
        String myRoleIds = StringUtil.integerListToString(roleIdList);

        userRoleService.updateUserRole(newUserId, myRoleIds);

        roleIdList.remove(newRoleId);
        myRoleIds = StringUtil.integerListToString(roleIdList);
        userRoleService.updateUserRole(newUserId, myRoleIds);

        // 4. 删除用户
        userService.removeUser(newUserId);
        // 5.通过用户名密码查找用户
        User userDB = userService.loadUser(userNew.getUserId());
        Assert.assertNull(userDB);

        // 4. 移除
        roleService.removeRole(newRoleId);

        // 3.2 通过角色ID查找角色
        Role roleDB = roleService.loadRole(newRoleId);
        Assert.assertNull(roleDB);
    }

}