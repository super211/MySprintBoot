package com.my.commons.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DataCheckAspect {
	
	@Pointcut("@annotation(com.my.commons.aspect.DataCheck)")//Here should point to the class path. Otherwise, error Type referred to is not an annotation type 
	public void checkPointcut(){}
	
	@Around("checkPointcut()")
	public Object measure(ProceedingJoinPoint joinPoint) throws Throwable{
		final Object result = joinPoint.proceed();
		System.err.println("Call from DataCheckASPECT. Executing Data Checking...1");
		return result;
	}
}
