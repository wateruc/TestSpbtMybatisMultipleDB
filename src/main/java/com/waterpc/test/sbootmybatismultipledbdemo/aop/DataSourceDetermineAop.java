package com.waterpc.test.sbootmybatismultipledbdemo.aop;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.waterpc.test.sbootmybatismultipledbdemo.annotation.DataSourceNameSetAnnotation;
import com.waterpc.test.sbootmybatismultipledbdemo.assist.DataSourceContextHolder;
import com.waterpc.test.sbootmybatismultipledbdemo.assist.DataSourceNameSetValues;

/**  
 * 在service层决定数据源  
 *   
 * 必须在事务AOP之前执行，所以实现Ordered,order的值越小，越先执行  
 *   
 *  
 */   
@Aspect
@EnableAspectJAutoProxy(exposeProxy=true,proxyTargetClass=true)
@Order(-1)
@Component
public class DataSourceDetermineAop {

	@Pointcut("execution(* com.waterpc.test.sbootmybatismultipledbdemo.service..*.*(..)) "   
            + " and @annotation(com.waterpc.test.sbootmybatismultipledbdemo.annotation.DataSourceNameSetAnnotation) ")
	public void datasoureNameSetPointCut(){
	}
	
	@Before("datasoureNameSetPointCut()")
    public void setDataSourceName(JoinPoint joinPoint) {   
        //如果已经开启写事务了，那之后的所有读都从写库读   
		
		Signature signature = joinPoint.getSignature();    
	    MethodSignature methodSignature = (MethodSignature)signature;    
	    Method targetMethod = methodSignature.getMethod();    
	    
	    //类上有注解，因为spring aop只能在方法调用时发挥效果，所以注解始终都要编写在方法上才能被spring aop识别到
	    Class<?> clazz = targetMethod.getDeclaringClass();
	    if(clazz.isAnnotationPresent(DataSourceNameSetAnnotation.class)){
	    	DataSourceNameSetAnnotation theAnnotaion1=clazz.getAnnotation(DataSourceNameSetAnnotation.class);
	    	String theAnnotationValue1=theAnnotaion1.value();
	    }
	    //方法上有注解
	    if(targetMethod.isAnnotationPresent(DataSourceNameSetAnnotation.class)){
	    	DataSourceNameSetAnnotation theAnnotaion=targetMethod.getAnnotation(DataSourceNameSetAnnotation.class);
	    	String theAnnotationValue=theAnnotaion.value();
	    	if(theAnnotationValue==null||theAnnotationValue==""){
	    		DataSourceContextHolder.setDataSource(DataSourceNameSetValues.MYSQL01);
	    	}else{
	    		DataSourceContextHolder.setDataSource(theAnnotationValue);
	    	}
	    }
    }

}
