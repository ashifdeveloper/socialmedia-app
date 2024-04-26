package com.zosh.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.crypto.SecretKey;
import java.util.Date;

public class JwtProvider {
    private static SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
    public static String generateToken(UsernamePasswordAuthenticationToken auth){
        String jwt = Jwts.builder()
        .setIssuer("Codewithzosh").setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime()+86400000))
                .claim("email",auth.getName())
                .signWith(key)
                .compact();
        return jwt;
    }
    public static String getEmailFromJwtToken(String jwt){
        //Bearer token
        jwt = jwt.substring(7);

        Claims claims = Jwts.parser()
                .setSigningKey(key).build().parseSignedClaims(jwt).getPayload();
        String email = String.valueOf(claims.get("email"));
        return email;

    }

}
