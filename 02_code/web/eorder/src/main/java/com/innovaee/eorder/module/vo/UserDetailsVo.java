/***********************************************
 * Filename        : UserDetailsVo.java 
 * Copyright      : Copyright (c) 2014
 * Company        : Innovaee
 * Created        : 11/27/2014
 ************************************************/

package com.innovaee.eorder.module.vo;

import com.innovaee.eorder.module.entity.User;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Title: UserDetailsVo
 * @Description: 用户详细信息值对象
 *
 * @version V1.0
 */
public class UserDetailsVo extends BaseVo implements UserDetails {

	/** 角色名称集合 */
	private Set<String> rolesName = new HashSet<String>();

	/** 用户 */
	private User user;

	/** 用户功能值对象列表 */
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

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
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