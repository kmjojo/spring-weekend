package com.hanbit.spring.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController {

	@RequestMapping("/")
	public String welcome() {

		// 에러 페이지 테스트
		/*if (true) {
			throw new RuntimeException("에러를 생성하였다.");
		}*/

		return "index";
	}
	
	@RequestMapping("/api/data")
	@ResponseBody
	public Map<String, String> getData() {
		
		Map<String, String> result = new HashMap<>();
		
		result.put("msg", "Hello Spring");
		
		// 에러 페이지 테스트
		/*
		if (result != null) {
			throw new RuntimeException("에러를 생성하였다.");
		}
		*/
		
		return result;
	}
}
