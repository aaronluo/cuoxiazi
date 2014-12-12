package com.innovaee.eorder.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import com.innovaee.eorder.bean.Category;

/**
 * 分类客户端，用来测试资源
 * 
 */
public class CategoryClient {

	private static String serverUri = "http://localhost:8080/eorder-ws/rest";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		addCategory();
		getAllCategorys();
		updateCategory();
		getCategoryById();
		getAllCategorys();
		delCategory();
		getAllCategorys();
	}

	/**
	 * 添加分类
	 */
	private static void addCategory() {
		System.out.println("****增加分类addCategory****");
		Category category = new Category("Susan");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serverUri + "/categorys");
		Response response = target.request()
				.buildPost(Entity.entity(category, MediaType.APPLICATION_JSON))
				.invoke();
		response.close();
	}

	/**
	 * 删除分类
	 */
	private static void delCategory() {
		System.out.println("****删除分类****");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serverUri + "/categorys/006");
		Response response = target.request().delete();
		response.close();
	}

	/**
	 * 修改分类
	 */
	private static void updateCategory() {
		System.out.println("****修改分类updateCategory****");
		Category category = new Category(10, "真的是测试");
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(serverUri + "/categorys");
		Response response = target.request()
				.buildPut(Entity.entity(category, MediaType.APPLICATION_JSON))
				.invoke();
		response.close();
	}

	/**
	 * 根据id查询分类
	 */
	private static void getCategoryById() {
		System.out.println("****根据id查询分类****");
		Client client = ClientBuilder.newClient().register(
				JacksonJsonProvider.class);// 注册json 支持
		WebTarget target = client.target(serverUri + "/categorys/006");
		Response response = target.request().get();
		Category category = response.readEntity(Category.class);
		System.out.println(category.getCategoryId()
				+ category.getCategoryName() + category.getCategoryPicture());
		response.close();
	}

	/**
	 * 查询所有分类
	 */
	private static void getAllCategorys() {
		System.out.println("****查询所有getAllCategorys****");

		Client client = ClientBuilder.newClient();

		WebTarget target = client.target(serverUri + "/categorys");
		Response response = target.request().get();
		String value = response.readEntity(String.class);
		System.out.println(value);
		response.close(); // 关闭连接
	}

}