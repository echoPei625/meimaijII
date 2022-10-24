package com.fang.meimaijII.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.fang.meimaijII.enums.ModuleEnum;


@Retention(RUNTIME)
@Target(METHOD)
@Documented
public @interface TokenVerify{
    ModuleEnum value();
}
