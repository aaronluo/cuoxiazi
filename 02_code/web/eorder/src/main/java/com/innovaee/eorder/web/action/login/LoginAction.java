package com.innovaee.eorder.web.action.login;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;

import com.innovaee.eorder.module.vo.RoleLinkVo;
import com.innovaee.eorder.module.vo.UserDetailsVo;
import com.innovaee.eorder.module.vo.UserFunctionVo;
import com.innovaee.eorder.web.action.BaseAction;

public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(LoginAction.class);

	private List<RoleLinkVo> menulist = new ArrayList<RoleLinkVo>();

	public String login() {
		return SUCCESS;
	}

	public String doLogin() {
		return SUCCESS;
	}

	public String doLogout() {
		return SUCCESS;
	}

	public String doHeader() {
		return SUCCESS;
	}

	public String doUserInfo() {
		return SUCCESS;
	}

	public String doRight() {
		return SUCCESS;
	}

	public String doBottom() {
		return SUCCESS;
	}

	/*
	 * add a function item into menu list
	 */
	public String doLeft() {
		// get user detail information from Spring Security Context
		UserDetailsVo userDetail = (UserDetailsVo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Assert.notNull(userDetail);

		// get all granted functions list of the authenticated user
		List<UserFunctionVo> userFunctions = userDetail.getUserFunctions();

		// sort the functions list in order by functions' order number
		Collections.sort(userFunctions, new Comparator<UserFunctionVo>() {
			@Override
			public int compare(UserFunctionVo o1, UserFunctionVo o2) {
				return o1.getFunction().getFunctionOrder().compareTo(o2.getFunction().getFunctionOrder());
			}
		});

		// fill the list<RoleLinkVo> in functions' list
		menulist.clear();
		for (UserFunctionVo ufVo : userFunctions) {
			if (StringUtils.isEmpty(ufVo.getFunction().getFunctionParent())) {
				// no parent menu item, this is a top level menu item

				// check if there is the duplicated item in the list
				boolean duplicated = false;
				for (RoleLinkVo r : menulist) {
					if (ufVo.getFunction().getFunctionName().equals(r.getName())) {
						duplicated = true;
						break;
					}
				}
				if (duplicated) {
					continue;
				}

				RoleLinkVo rlVo = new RoleLinkVo();
				rlVo.setFlag("1");
				rlVo.setLink(ufVo.getFunction().getFunctionPath());
				rlVo.setName(ufVo.getFunction().getFunctionName());
				rlVo.setDisplayName(ufVo.getFunction().getFunctionDisplay());
				rlVo.setOrder(ufVo.getFunction().getFunctionOrder());
				rlVo.setList(new ArrayList<RoleLinkVo>());
				menulist.add(rlVo);
				continue;
			}

			// otherwise, it's a level2 menu item
			RoleLinkVo parent = null;
			for (RoleLinkVo p : menulist) {
				if (p.getName().equals(ufVo.getFunction().getFunctionParent())) {
					parent = p;
					break;
				}
			}
			if (null == parent) {
				// cannot find the funcion item's parent
				logger.warn(String.format("cannot find function[%s]'s parent", ufVo.getFunction().getFunctionName()));
				continue;
			}

			// check if there is the duplicated item in the list
			boolean duplicated = false;
			for (RoleLinkVo r : parent.getList()) {
				if (ufVo.getFunction().getFunctionName().equals(r.getName())) {
					duplicated = true;
					break;
				}
			}
			if (duplicated) {
				continue;
			}
			RoleLinkVo rlVo = new RoleLinkVo();
			rlVo.setFlag("2");
			rlVo.setLink(ufVo.getFunction().getFunctionPath());
			rlVo.setName(ufVo.getFunction().getFunctionName());
			rlVo.setDisplayName(ufVo.getFunction().getFunctionDisplay());
			rlVo.setOrder(ufVo.getFunction().getFunctionOrder());
			parent.getList().add(rlVo);
		}

		return SUCCESS;
	}

	public List<RoleLinkVo> getMenulist() {
		return menulist;
	}

	public void setMenulist(List<RoleLinkVo> menulist) {
		this.menulist = menulist;
	}
}