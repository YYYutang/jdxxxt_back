package com.example.server.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
//根据用户信息生成Token
@Component
public class JwtTokenUtil {
    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";
    @Value("${jwt.secret}")
    private String secret;
    private  long expiration=7*24*3600*1000;
    public String generateToken(UserDetails userDetails){
        Map<String,Object>claims=new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
         return generateToken(claims);
    }
    //从token中获取登陆用户名
    public String getUserNameFromToken (String token){
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        }catch(Exception e){
            username=null;
        }
        return username;
    }
    //验证token是否有效
    public boolean validateToken(String token,UserDetails userDetails){
        String username=getUserNameFromToken(token);
        return username.equals(userDetails.getUsername())&&!isTokenExpired(token);
    }
    //判断token是否可以被刷新
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }
    //刷新token
    public String refreshToken(String token){
        Claims claims=getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }
    //判断token是否失效
    private boolean isTokenExpired(String token) {
        Date expireDate= getExpriedDataFromToken(token);
        return expireDate.before(new Date());
    }
    //从token中获取过期时间
    private Date getExpriedDataFromToken(String token) {
    Claims claims=getClaimsFromToken(token);
    return claims.getExpiration();
    }
    //从token中获得荷载
    private Claims getClaimsFromToken(String token) {
        Claims claims=null;
       try {
           claims = Jwts.parser()
                   .setSigningKey(secret)
                   .parseClaimsJws(token)
                   .getBody();
       }catch (Exception e){
            e.printStackTrace();
       }
       return claims;
    }

    //根据荷载生成JWT TOKEN
    private String generateToken(Map<String,Object>claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    private Date generateExpirationDate() {
        System.out.print(expiration);
        return new Date(System.currentTimeMillis()+expiration+1000);
    }
}
