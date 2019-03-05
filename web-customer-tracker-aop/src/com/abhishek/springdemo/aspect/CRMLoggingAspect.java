package com.abhishek.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	//setup pointcut declaration
	@Pointcut("execution(* com.abhishek.springdemo.controller.*.*(..))")
	private void forControllerPackage(){}
	
	@Pointcut("execution(* com.abhishek.springdemo.service.*.*(..))")
	private void forServicePackage(){}
	
	@Pointcut("execution(* com.abhishek.springdemo.dao.*.*(..))")
	private void forDaoPackage(){}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow(){} 
	
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint){
		String theMethod = theJoinPoint.getSignature().toString();
		myLogger.info("=====>> In @Before advice calling method: "+theMethod);
		
		Object[] args = theJoinPoint.getArgs();
		
		for(Object tempArg: args){
			myLogger.info("=====> argument: "+tempArg);
		}
		
	}
	
	@AfterReturning(pointcut="forAppFlow()",returning="theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult){
		
		String theMethod = theJoinPoint.getSignature().toString();
		myLogger.info("=====>> In @AfterReturnin advice from method: "+theMethod);
		
		myLogger.info("=====>> Result: "+theResult);
	}

	
}






