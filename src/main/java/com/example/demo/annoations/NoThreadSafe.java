package com.example.demo.annoations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @program: demo5
 * @description: 用来标记线程不安全的类 使用的注解
 * @author: Mr.Wang
 * @create: 2022-04-03 01:20
 **/

/**
 * Target 作用的目标
 * ElementType  .TYPE 对应的是代表 类
 * 线程安全的
 * Retention 注解存在的范围
 * RetentionPolicy.runtime  代表注解会在class 字节码文件存在
 * 可以通过反射拿到注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface NoThreadSafe {
    String value() default "";
}
