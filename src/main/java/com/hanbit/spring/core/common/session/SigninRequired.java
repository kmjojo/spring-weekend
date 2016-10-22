package com.hanbit.spring.core.common.session;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
public @interface SigninRequired {
	
	String[] auth() default {};	// userController에서 @SigninRequired(auth={"MEMBER", "MANAGER", "ADMIN"}) 권한 처리 가능!
	// reflect 확인하면 이 안에 들어있는 값을 확인할 수 있다.

}
