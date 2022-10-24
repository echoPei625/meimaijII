package com.fang.meimaijII.aspects;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fang.meimaijII.annotations.TokenVerify;
import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.utils.JwtUtils;
import com.fang.meimaijII.utils.MeimaijIIException;

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
    public void pointcut(){
    }

    @Before("pointcut()")
    public void tokenVerify(JoinPoint joinpoint) throws Throwable{
        HttpServletRequest request = ( (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes() ).getRequest();
        Map<String, Object> map = jwtUtils.verifyToken(request);
        List<Long> moduleIds = (List<Long>) map.get("moduleIds");
        Method method = ( (MethodSignature) joinpoint.getSignature() ).getMethod();
        TokenVerify anno = method.getAnnotation(TokenVerify.class);
        if (!moduleIds.contains(anno.value().getCode())){
            LOGGER.error(map.get("jti").toString() + " 沒有權限");
            throw new MeimaijIIException("無此功能", ResponseCode.TOKEN_ISSUE.getCode());
        }
    }
}
