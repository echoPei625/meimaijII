package com.fang.meimaijII.aspects;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fang.meimaijII.utils.JwtUtils;

@Aspect
@Order(2)
@Component
public class AuthrizationAspect{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthrizationAspect.class);

    @Value("${jwt.secret}")
    private String secret;

    @Autowired
    private JwtUtils jwtUtils;
    
    @Pointcut("@annotation(com.fang.meimaijII.annotations.TokenVerify)")
    public void pointcut() {}
    
    @Before("pointcut()")
    public void tokenVerify(JoinPoint joinpoint) throws Throwable{
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Map<String, Object> map = jwtUtils.verifyToken(request);
        for(String s:map.keySet()) {
            System.out.println(s + " value is" + map.get(s));
        }
    }
    
}
