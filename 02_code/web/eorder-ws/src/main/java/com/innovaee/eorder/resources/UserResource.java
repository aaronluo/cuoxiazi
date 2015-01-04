/***********************************************
 * Filename		: UserResource.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.resources;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
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
 * @Title: UserResource
 * @Description: 用户资源
 * @version V1.0
 */
@Path("/users")
public class UserResource extends AbstractBaseResource {

	/** 用户数据访问实现类对象 */
	private UserDaoImpl userDaoImpl = new UserDaoImpl();

	/** 用户等级数据访问实现类对象 */
	private UserLevelDaoImpl userLevelDaoImpl = new UserLevelDaoImpl();

	/**
	 * 根据手机号码查询用户信息
	 * 
	 * @param cellphone
	 *            手机号码
	 * @return 用户值对象
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
				LOGGER.error(e.getMessage());
			} catch (InvocationTargetException e) {
				LOGGER.error(e.getMessage());
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

}