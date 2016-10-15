package com.hanbit.spring.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hanbit.spring.core.service.UserService;
import com.hanbit.spring.core.vo.UserVO;

@RestController	// controller + responseBody
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/list") 
	public List<UserVO> listUsers() {
		
		List<UserVO> list = userService.listUsers();
		
		return list;
	}
	
}
