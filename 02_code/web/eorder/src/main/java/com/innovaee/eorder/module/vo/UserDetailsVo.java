/***********************************************
 * Filename		: UserDetailsVo.java																									: DishService.java
 * Copyright  	: Copyright (c) 2014
 * Company    	: Innovaee
 * Created	    : 11/27/2014
 ************************************************/
package com.innovaee.eorder.module.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import com.innovaee.eorder.module.entity.User;

/**   
* @Title: UserDetailsVo 
* @Description: 用户详细信息值对象
* @author coderdream@gmail.com   
* @version V1.0   
*/
public class UserDetailsVo extends BaseVo implements UserDetails {

	private static final long serialVersionUID = 1L;

	private Set<String> rolesName = new HashSet<String>();
	private User user;
	private List<UserFunctionVo> userFunctions;

	public List<UserFunctionVo> getUserFunctions() {
		return userFunctions;
	}

	public void setUserFunctions(List<UserFunctionVo> userFunctions) {
		this.userFunctions = userFunctions;

		if (!userFunctions.isEmpty()) {
			user = userFunctions.get(0).getUser();

			rolesName.clear();
			for (UserFunctionVo userFunctionVo : userFunctions) {
				rolesName.add(userFunctionVo.getRole().getRoleName());
			}
		}
	}

	public Collection<GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		for (UserFunctionVo ufv : userFunctions) {
			if (!StringUtils.isEmpty(ufv.getFunction().getFunctionPath())) {
				GrantedAuthority grantedAuthority = new GrantedAuthorityImpl(
						ufv.getFunction().getFunctionPath());
				grantedAuthorities.add(grantedAuthority);
			}
		}

		return grantedAuthorities;
	}

	public String getPassword() {
		return user.getPassword();
	}

	public String getUsername() {
		return user.getUsername();
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		return user.getUserStatus();
	}

	public Set<String> getRolesName() {
		return rolesName;
	}

	public User getUser() {
		return user;
	}

}