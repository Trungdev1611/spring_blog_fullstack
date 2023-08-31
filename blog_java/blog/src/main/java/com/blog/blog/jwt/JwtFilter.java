package com.blog.blog.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.blog.blog.ErrorDTO.ErrorResponse;
import com.blog.blog.auth.UserRepository;
import com.blog.blog.exception.ApiException;
import com.blog.blog.exception.ResourceNotFoundEx;
import com.blog.blog.exception.TokenException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
// nhớ add filter này vào config
public class JwtFilter extends OncePerRequestFilter {
    @Qualifier("handlerExceptionResolver") // lỗi từ filter sẽ không được bắt ở GlobalException nên ta phải dùng cái này
                                           // + catch trong JwtFilter ném ra lỗi thì lỗi đó mới được bắt

    @Autowired
    private HandlerExceptionResolver resolver;// lỗi từ filter sẽ không được bắt ở GlobalException nên ta phải dùng cái
                                              // này
                                              // + catch trong JwtFilter ném ra lỗi thì lỗi đó mới được bắt
    JwtProvider jwtProvider;
    UserDetailsService userDetailsService;

    @Autowired
    public JwtFilter(JwtProvider jwtProvider, UserDetailsService userDetailsService) {
        this.jwtProvider = jwtProvider;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        try {
            String token = jwtProvider.getTokenFromRequestHeader(request);
            System.out.println("di qua day");
            // System.out.println("1111 " + jwtProvider.verifyToken(token));
            if (token != null && !token.trim().isEmpty() && jwtProvider.verifyToken(token)) {
                String usernameGetFromToken = jwtProvider.getPayloadfromtoken(token);

                // dựa vào username để lấy được thông tin user
                UserDetails userDetail = userDetailsService.loadUserByUsername(usernameGetFromToken);

                // set userDetai vào đối tượng của UsernamePasswordAuthenticationToken
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetail, null, userDetail.getAuthorities());

                // thêm bảo mật
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                // set vào SecurityContextHolder để lưu user tìm được vào context. Từ đây nó có
                // thể truy cập được vào tài nguyên
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }

            else { //bắt lỗi không có token với những request khác login và register
            String reqPath = request.getRequestURI();  
            System.out.println("URL request:::"+ reqPath );
            if ((token == null || reqPath.trim().isEmpty()) && !reqPath.contains("login")
                    && !reqPath.contains("register")) {
                throw new TokenException("Không tồn tại token");
            }
            }
           
            // tiếp tục thực hiện những filter khác
            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // custom error response class used across my project

            System.out.println("Spring Security Filter Chain Exception:" +
                    e.getMessage());
            resolver.resolveException(request, response, null, e); // phải ném bằng resolver mới được bắt ở
                                                                   // GlobalException
            // throw new ApiException("Lỗi chưa định nghĩa: Orther error");
        }

    }

}
