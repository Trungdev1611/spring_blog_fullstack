package com.blog.blog.jwt;

import java.security.Key;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.blog.blog.exception.ApiException;
import com.blog.blog.exception.TokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

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

    public String getPayloadfromtoken(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(sceretKey));
    }

    public Boolean verifyToken(String tokenValidate) {
        // phải ném bằng @Qualifier("handlerExceptionResolver") và
        // resolver.resolveException(request, response, null, e); thì mới bắt được lỗi ở
        // đây
        System.out.println("token validate:: " + tokenValidate);
        if (tokenValidate == null) {
            return false;
        }
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(tokenValidate);
            return true;
        } catch (MalformedJwtException ex) {
            throw new TokenException("InvalidJwt");
        } catch (ExpiredJwtException ex) {
            throw new TokenException("Expired Jwt token");
        } catch (UnsupportedJwtException ex) {
            throw new TokenException("Unsupport JWT token");
        } catch (IllegalArgumentException ex) {
            throw new TokenException("JWT claims string is empty");
        } catch (Exception ex) {
            // throw new TokenException("loi chua dinh nghia");
            throw new TokenException("Lỗi chưa định nghĩa: Orther error");

        }

    }

    public String getTokenFromRequestHeader(HttpServletRequest request) {
        // láy bearer token từ header authorization request
        String headerBearerRequest = request.getHeader("Authorization");
        System.out.println("token header bearer:: " + headerBearerRequest);
        if (headerBearerRequest != null && !headerBearerRequest.isEmpty()
                && headerBearerRequest.startsWith("Bearer ")) {
            return headerBearerRequest.substring(7);
        }

        return null;
    }
}
