package com.waterpc.test.sbootmybatismultipledbdemo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.waterpc.test.sbootmybatismultipledbdemo.assist.DataSourceNameSetValues;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target({ElementType.METHOD, ElementType.TYPE})   
@Retention(RetentionPolicy.RUNTIME)   
@Inherited   
@Documented   
public @interface DataSourceNameSetAnnotation {
	String value() default DataSourceNameSetValues.MYSQL01;
}
