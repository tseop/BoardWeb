package com.springbook.biz.user;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserServiceClient {

	public static void main(String[] args) {

		AbstractApplicationContext container = new GenericXmlApplicationContext("applicationContext.xml");

		UserService userService = (UserService) container.getBean("userService");

		UserVO vo = new UserVO();
		vo.setId("test1");
		vo.setPassword("test123");

		UserVO user = userService.getUser(vo);

		String role = null;

		if (user.getRole().equals("admin")) {
			role = "관리자님";
		} else {
			role = "유저님";
		}

		if (user != null) {
			System.out.println("Welcome '" + user.getName() + "' " + role);
		} else {
			System.out.println("login failed");
		}

		container.close();
	}
}
