package com.hanbit.spring.core.common.session;

import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(2)
public class SessionAspect {

	@Around("@annotation(com.hanbit.spring.core.common.session.SigninRequired)")
	public Object checkSignin(ProceedingJoinPoint pjp) throws Throwable {
		Map session = SessionHelper.getSession();	// RequestContextHolder를 써서 가져와도 욈
		
		if (session.get("loggedIn") == null) {
			MethodSignature signature = (MethodSignature) pjp.getSignature();
			Class returnType = signature.getReturnType();
			
			if (returnType == String.class) {
				return "login";
			}
			
			throw new RuntimeException("로그인이 필요합니다.");
			
		}
		
		Object returnValue = pjp.proceed();	// 위에서 걸러지지 않으면 계속 실행
		
		return returnValue;
	}
	
}
