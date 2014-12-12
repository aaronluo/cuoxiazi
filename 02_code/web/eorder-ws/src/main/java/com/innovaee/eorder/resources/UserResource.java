package com.innovaee.eorder.resources;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;

import com.innovaee.eorder.bean.User;
import com.innovaee.eorder.bean.UserLevel;
import com.innovaee.eorder.dao.impl.UserDaoImpl;
import com.innovaee.eorder.dao.impl.UserLevelDaoImpl;
import com.innovaee.eorder.vo.UserVO;

/**
 * 用户资源
 * 
 */
@Path("/users")
public class UserResource {
	private UserDaoImpl userDaoImpl = new UserDaoImpl();

	private UserLevelDaoImpl userLevelDaoImpl = new UserLevelDaoImpl();

	/**
	 * 增加
	 * 
	 * @param user
	 */
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void createUser(User user) {
		userDaoImpl.createUser(user);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	@DELETE
	@Path("{id}")
	public void deleteUser(@PathParam("id") String id) {
		userDaoImpl.deleteUserById(id);
	}

	/**
	 * 修改
	 * 
	 * @param user
	 */
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void updateUser(User user) {
		userDaoImpl.updateUser(user);
	}

	/**
	 * 根据categoryId查询
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("/myuser/{cellphone}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, UserVO> getUseresById(
			@PathParam("cellphone") String cellphone) {
		User user = userDaoImpl.getUserByCellphone(cellphone);
		UserVO userVO = new UserVO();
		
		if (null != user) {
			try {
				BeanUtils.copyProperties(userVO, user);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			UserLevel userLevel = null;
			userLevel = userLevelDaoImpl.getUserLevelById(user.getLevelId()
					.toString());
			userVO.setDiscount(userLevel.getDiscount());
			userVO.setLevelName(userLevel.getLevelName());
		}

		Map<String, UserVO> result = new HashMap<String, UserVO>();
		result.put("user", userVO);
		return result;
	}

	/**
	 * 根据id查询
	 * 
	 * @param id
	 * @return
	 */
	@GET
	@Path("{userId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, User> getUserById(@PathParam("userId") String userId) {
		User user = userDaoImpl.getUserById(userId);
		Map<String, User> result = new HashMap<String, User>();
		result.put("user", user);

		return result;
	}

	/**
	 * 查询所有
	 * 
	 * @return
	 */
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Map<String, List<User>> getAllUsers() {
		List<User> users = new ArrayList<User>();
		users = userDaoImpl.getAllUseres();
		Map<String, List<User>> result = new HashMap<String, List<User>>();
		result.put("users", users);
		return result;
	}

}