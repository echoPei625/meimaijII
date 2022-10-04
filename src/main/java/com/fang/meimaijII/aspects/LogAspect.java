package com.fang.meimaijII.aspects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Order(1)
@Component
public class LogAspect{

    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Autowired
    ObjectMapper mapper;

    @Pointcut("execution(* com.fang.meimaijII.controllers..*(..))")
    public void pointcut(){
    }

    @Before("pointcut()")
    public void before(JoinPoint joinPoint) throws Throwable{
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.debug("[{}] [{}] [{}] ", request.getRequestURI(), request.getMethod(), request.getHeader("Content-Type"));
        //LOGGER.debug("-----------------REQUEST------------------");
        if (request.getParameterMap() != null || !request.getParameterMap().isEmpty()){
            LOGGER.debug("request parameter: {}", mapper.writeValueAsString(request.getParameterMap()));
        }
        if ("application/json".equals(request.getHeader("Content-Type"))){
            //記錄application/json時的傳參，SpringMVC中使用@RequestBody接收的值
            LOGGER.debug("request body: {}", joinPoint.getArgs());
        }
    }

    @AfterReturning(pointcut = "pointcut()")
    public void doAfterReturning() throws Throwable{
        // 處理完請求，返回內容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        LOGGER.debug("[{}] [{}] DONE SUCCESSFULLY ", request.getRequestURI(), request.getMethod());
    }
}
