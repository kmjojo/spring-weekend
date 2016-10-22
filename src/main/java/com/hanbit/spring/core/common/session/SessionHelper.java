package com.hanbit.spring.core.common.session;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SessionHelper {
	
	private static SessionHelper SESSION_HELPER;	// 싱클톤 방식의 스태틱 오브젝트 생성. getBean을 계속 하기 위해 씀
	// Autowired 를 쓰면 한 번만 getBean됨. 매 번 새로 만들어야 하는데 한 번만 만들어지므로 문제가 됨.
	
	@Autowired
	private ApplicationContext applicationContext;
	
	@PostConstruct
	public void initSessionHelper() {
		SESSION_HELPER = this;
	}

	public static Map<String, Object> getSession() {
		return SESSION_HELPER.applicationContext
				.getBean(SpringSession.class).getSession();
	}
	
	
	
}
