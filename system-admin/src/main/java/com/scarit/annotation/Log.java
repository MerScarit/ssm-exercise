package com.scarit.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Log {
    //具体的日志的内容
    String tittle() default "";
    //日志业务的分类，便于条件筛选
    String businessType() default "";


}
