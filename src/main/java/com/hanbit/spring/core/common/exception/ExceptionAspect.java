package com.hanbit.spring.core.common.exception;

import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
@Aspect
@Order(1)	// 숫자가 낮은 순서대로 먼저 처리
public class ExceptionAspect {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionAspect.class);
	
	// 웹 요청을 받고 그 요청을 처리하는 중에 발생한 exception을 처리한다.
	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public Object handleException(ProceedingJoinPoint pjp) throws Throwable {
		
		Object returnValue = null;
		
		try {

			return pjp.proceed();
			
		} catch (Throwable t) {
			LOGGER.error(t.getMessage(), t);	// 에러 메시지와 트레이스 출력
			
			// 클래스의 리턴 타입을 알아야 한다.
			MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
			
			Class returnType = methodSignature.getReturnType();
			
			// 에러 페이지를 전송하거나 밑에 가서 json에러 메시지 전송
			if (returnType == String.class) {
				throw t;
			}
			
			// json으로 에러 메시지 출력
			RequestAttributes reqAttrs = RequestContextHolder.currentRequestAttributes(); // request와 response를 저장해놓은 것을 가져온다.
			ServletRequestAttributes servletReqAttrs = (ServletRequestAttributes) reqAttrs;
			
			HttpServletResponse res = servletReqAttrs.getResponse();
			
			// sendError()를 쓰면 톰캣이 error를 잡아서 톰캣 에러 페이지를 출력한다. 안예쁨
			//res.sendError();
			
			String errorJson = "{\"errorMsg\":\"" + t.getMessage() + "\"}";
			
			res.setStatus(1500, t.getMessage());
			res.setContentType("application/json; charset=utf-8");
			res.getOutputStream().write(errorJson.getBytes());
			res.flushBuffer();
			
		}
		
		return null;
	}
}
