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
				GrantedAuthority grantedAuthority = new GrantedAuthorityImpl(ufv.getFunction().getFunctionPath());
				grantedAuthorities.add(grantedAuthority);
			}
		}

		return grantedAuthorities;
	}

	public String getPassword() {
		return user.getUserPassword();
	}

	public String getUsername() {
		return user.getUserName();
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
		return user.getUserEnable();
	}

	public Set<String> getRolesName() {
		return rolesName;
	}

	public User getUser() {
		return user;
	}

}