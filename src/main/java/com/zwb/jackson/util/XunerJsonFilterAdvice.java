package com.zwb.jackson.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Map;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@org.aspectj.lang.annotation.Aspect
public class XunerJsonFilterAdvice {

	@Pointcut(value="execution(* com.zwb.action.*.*(..))")
	public void PointCut(){
		System.out.println("PointCut");
	}
	
	@Around(value="PointCut()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable{
		System.out.println("进入环绕");
		 MethodSignature msig = (MethodSignature) pjp.getSignature();
		 XunerJsonFilter annotation = msig.getMethod().getAnnotation(
	                XunerJsonFilter.class);
	        XunerJsonFilters annotations = msig.getMethod().getAnnotation(
	                XunerJsonFilters.class);
	        if (annotation == null && annotations == null) {
	            return pjp.proceed();
	        }
	        if (annotations != null) {
	    	   XunerJsonFilter[] filters= annotations.value();
	    	   Arrays.asList(filters).forEach(filter->{
	    		   Class mixin = filter.mixin();
	    		   String[] properties = filter.target();
	    		   Arrays.asList(properties).forEach(property->{
	    			   try {
						Field field = mixin.getDeclaredField(property);
						   //获取 foo 这个代理实例所持有的 InvocationHandler 
						if(!field.isAnnotationPresent(JsonIgnore.class)){
//							InvocationHandler invocationHandler = Proxy.getInvocationHandler(JsonIgnore.class);
//							Map memberValues = (Map) field.get(invocationHandler);
//							memberValues.put("value", true); 
						}else{
							//动态修改注解
						Annotation anno = (Annotation) (field.getAnnotation(JsonIgnore.class));
						changeAnnotationValue(anno,"value",true);
//						
						}
	    			   } catch (Exception e) {
						e.printStackTrace();
					}
	    		   });
	    		  
	    	   });
	          }
	       return pjp.proceed();
	    }
	
	  public static void changeAnnotationValue(Annotation annotation, String key, Object newValue) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {  
		  InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
	        Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");  
	        if(declaredField==null) return;
	        declaredField.setAccessible(true);  
	        // 获取 memberValues  
	        Map memberValues = (Map) declaredField.get(invocationHandler);  
	        // 修改 value 属性值  
	        memberValues.put(key, newValue); 
	  }
}
