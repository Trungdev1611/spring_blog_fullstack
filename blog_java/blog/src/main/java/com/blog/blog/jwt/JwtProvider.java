package com.blog.blog.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component // Annotation đánh dấu class JWTTokenProvider là một Spring bean và được quản lý
           // bởi Spring IoC container.
public class JwtProvider {
    private String sceretKey = "2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b";
    private Long timeExpired = 60480000L;

    public String generateToken(Authentication authentication) {

        String username = authentication.getName();

        System.out.println("username::::" + username);

        Date currentDate = new Date();
        // end date - thời gian hết hạn kể từ lúc tạo token
        Date expireDate = new Date(currentDate.getTime() + timeExpired);

        String token = Jwts.builder()
                .setSubject(username) // setpayload
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(sceretKey));
    }

    public String generateTokenTest(String test) {

        String username = test;

        System.out.println("username::::" + username);

        Date currentDate = new Date();
        // end date - thời gian hết hạn kể từ lúc tạo token
        Date expireDate = new Date(currentDate.getTime() + timeExpired);

        String token = Jwts.builder()
                .setSubject(username) // setpayload
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return token;
    }

}
