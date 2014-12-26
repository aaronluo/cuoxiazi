package com.innovaee.eorder.module.service.security;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;

import com.innovaee.eorder.module.entity.Function;
import com.innovaee.eorder.module.vo.UserFunctionVo;
import com.innovaee.eorder.test.BaseSpringTestCase;

public class SecurityMetadataSourceServiceTest extends BaseSpringTestCase {

	@Autowired
	private SecurityMetadataSourceService securityMetadataSourceService;

	@Test
	public void getAllFunctions() {
		List<Function> allFunctions = securityMetadataSourceService
				.getAllFunctions();
		Assert.assertNotNull(allFunctions);
		for (Function function : allFunctions) {
			System.out.println(function);
		}
	}

	@Test
	public void getUserFunctions_01() {
		String username = "admin";
		List<UserFunctionVo> allUserFunctionVos = securityMetadataSourceService
				.getUserFunctions(username);
		Assert.assertNotNull(allUserFunctionVos);
		for (UserFunctionVo userFunctionVo : allUserFunctionVos) {
			System.out.println(userFunctionVo);
		}
	}

	@Test
	public void getUserFunctions_02() {
		String username = "test";
		List<UserFunctionVo> allUserFunctionVos = securityMetadataSourceService
				.getUserFunctions(username);
		Assert.assertNotNull(allUserFunctionVos);
		for (UserFunctionVo userFunctionVo : allUserFunctionVos) {
			System.out.println(userFunctionVo);
		}
	}

	@Test
	public void getAllConfigAttributes() {

		Collection<ConfigAttribute> c = securityMetadataSourceService
				.getAllConfigAttributes();
		Iterator<ConfigAttribute> iterator = c.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			String attribute = configAttribute.getAttribute();
			System.out.println(attribute);
		}

	}

}
