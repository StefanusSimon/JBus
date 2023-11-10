package com.StefanusSimonJBusRS.dbjson;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 Stefanus Simon - 2206830422
 Modul 7
 */
@Retention(value=RetentionPolicy.RUNTIME)
@Target(value={ElementType.FIELD})
public @interface JsonAutowired
{
	Class<?> value();
	String filepath();
}