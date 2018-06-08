package com.zwb.util;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@org.aspectj.lang.annotation.Aspect
public class Aspect {
	@Pointcut(value="execution(* com.zwb.test.spring.*.*(..))")
	public void PointCut(){
		System.out.println("PointCut");
	}
	
	
	@Before(value = "PointCut()")
	public void beforeAspect(JoinPoint jp){
		String methodName = jp.getSignature().getName();
        System.out.println("【前置通知】the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
   
	}
	
	@After(value="PointCut()")
	public void afterAspect(JoinPoint jp){
	System.out.println("【后置通知】this is a afterMethod advice...");
	}
	
	@AfterReturning(value="PointCut()",returning="result")
	public void afterReturning(JoinPoint jp, Object result){
		  String methodName = jp.getSignature().getName();
	        System.out.println("【返回通知】the method 【" + methodName + "】 ends with 【" + result + "】");
	    }
	
	@AfterThrowing(value="PointCut()",throwing="e")
	public void afterThrowing(JoinPoint jp,Exception e){
		  String methodName = jp.getSignature().getName();
	        System.out.println("【异常通知】the method 【" + methodName + "】 occurs exception: " + e);
	    	}
	
	@Around(value = "PointCut()")
	public void aroundAspect(ProceedingJoinPoint jp){
//		  Object result = null;
		String methodName = jp.getSignature().getName();
	      try {
	          System.out.println("【环绕通知中的--->前置通知】：the method 【" + methodName + "】 begins with " + Arrays.asList(jp.getArgs()));
	          //执行目标方法
	          long start = System.currentTimeMillis();
	          jp.proceed();
	          long end = System.currentTimeMillis();
	          System.out.println("time:"+(end-start));
	          System.out.println("【环绕通知中的--->返回通知】：the method 【" + methodName + "】 ends with ");
	      } catch (Throwable e) {
	          System.out.println("【环绕通知中的--->异常通知】：the method 【" + methodName + "】 occurs exception " + e);
	      }
	      
	      System.out.println("【环绕通知中的--->后置通知】：-----------------end.----------------------");
//	      return result;
	}
}
