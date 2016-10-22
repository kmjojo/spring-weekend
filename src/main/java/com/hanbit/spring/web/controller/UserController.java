package com.hanbit.spring.web.controller;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
	
	@RequestMapping("")
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
	
	@RequestMapping(value = "/add", method={RequestMethod.POST, RequestMethod.PUT})
	@ResponseBody
	public UserVO addUser(@RequestBody UserVO userVO) {
		
		userService.signUpUser(userVO);
		
		userVO.setUserPassword(StringUtils.EMPTY);// 여기에 "sodyd~" 이렇게 넣으면 객체가 생성되기 때문에 상수를 넣는다.
		
		//request.getFileName 이 NULL이면 파일이 등록되지 않았다는 것을 알 수 있다. 따라서 request.getFile()에 NullPointException이 발생할 것이다.
		// Iterator<String> fileIds = request.getfileNames();
		// while (fileIds.hasNext()) {/*다음다음~*/}	// 여기서 걸리면 아무 것도 없는 것임
		
		return userVO;
	}
	
}
