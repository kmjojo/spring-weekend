package com.hanbit.spring.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hanbit.spring.core.service.UserService;
import com.hanbit.spring.core.vo.UserVO;

//@RestController	// controller + responseBody
@Controller
@RequestMapping("/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/")
	public String userList() {

		LOGGER.info("userList");
		
		return "/user/list";
	}
	
	
	@RequestMapping("/list")
	@ResponseBody
	public List<UserVO> listUsers() {

		LOGGER.info("listUsers");
		
		/*
		 * 
		// ajax 에러 테스트
		if (true) {
			throw new RuntimeException("에러를 생성하였다.");
		}
		*/
		
		
		return userService.listUsers();
	}
	
}
