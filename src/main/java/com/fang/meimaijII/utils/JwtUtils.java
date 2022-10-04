package com.fang.meimaijII.utils;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fang.meimaijII.entities.Role;
import com.fang.meimaijII.entities.User;
import com.fang.meimaijII.enums.ResponseCode;
import com.fang.meimaijII.repositories.RoleRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtils{

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long tokenExpirationTime;

    @Value("${jwt.refreshTokenExpireTime}")
    private Long refreshTokenExpirationTime;

    @Autowired
    private RoleRepository roleRepository;

    public static final String HEADER_STRING = "Authorization";

    public static final String TOKEN_PREFIX = "Bearer ";

    public Map<String, Object> generate(User user, Date now){
        Role role = roleRepository.findById(user.getRoleId()).orElseThrow(() -> new MeimaijIIException("查無使用者角色", ResponseCode.DATA_NOT_FOUND.getCode()));
        List<Long> moduleIds = role.getModule().stream().map((x) -> x.getId()).collect(Collectors.toList());
        Map<String, Object> result = new HashMap<String, Object>();
        Date expiretime = new Date(now.getTime() + tokenExpirationTime);
        String token = Jwts.builder().setExpiration(expiretime).setId(user.getUuid()).setIssuedAt(now).setSubject(user.getAccount()).claim("moduleIds", moduleIds).claim("roleId", role.getId()).claim("roleName", role.getName()).signWith(SignatureAlgorithm.HS512, secret).compact();
        result.put("token", token);
        result.put("expireTime", expiretime);
        String refreshToken = Jwts.builder().setId(user.getUuid()).setIssuedAt(now).setExpiration(new Date(now.getTime() + refreshTokenExpirationTime)).signWith(SignatureAlgorithm.HS512, secret).compact();
        result.put("refreshToken", refreshToken);
        return result;
    }

    public Map<String, Object> verifyToken(HttpServletRequest request){
        String tokenRow = request.getHeader(HEADER_STRING);
        if (tokenRow == null || tokenRow.isBlank() || !tokenRow.startsWith(TOKEN_PREFIX)){
            throw new MeimaijIIException("未帶token", ResponseCode.FORBIDAN.getCode());
        }
        String token = tokenRow.substring(TOKEN_PREFIX.length());
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("result", false);
        try{
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secret)).parseClaimsJws(token).getBody();
            result.putAll(claims);
            result.replace("result", false, true);
        }catch(SignatureException e){
            throw new MeimaijIIException("Invalid Jwt signature", ResponseCode.FORBIDAN.getCode());
        }catch(MalformedJwtException e){
            throw new MeimaijIIException("Invalid Jwt token", ResponseCode.FORBIDAN.getCode());
        }catch(ExpiredJwtException e){
            throw new MeimaijIIException("Expired Jwt token", ResponseCode.FORBIDAN.getCode());
        }catch(UnsupportedJwtException e){
            throw new MeimaijIIException("Unsupported Jwt token", ResponseCode.FORBIDAN.getCode());
        }catch(IllegalArgumentException e){
            throw new MeimaijIIException("Jwt claims string is empty", ResponseCode.FORBIDAN.getCode());
        }
        return result;
    }
}
