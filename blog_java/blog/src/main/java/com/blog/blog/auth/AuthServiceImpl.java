package com.blog.blog.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;
import com.blog.blog.jwt.JwtProvider;

@Service
public class AuthServiceImpl implements AuthService {
    public AuthenticationManager authenticationManager;
    public JwtProvider jwtTokenProvider;

    @Autowired
    public AuthServiceImpl(AuthenticationManager authenticationManager,
            JwtProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDTO loginDTO) {

        // set body từ request vào authenticcationManager
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword());

        System.out.println("aaa1" + usernamePasswordAuthenticationToken);
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // đặt thông tin xác thực vào context bảo mật của Spring Security, để ứng dụng
        // biết rằng người dùng đã xác thực thành công.
        // Chẳng hạn, có thể sử dụng annotations như @Secured, @PreAuthorize,
        // @PostAuthorize để kiểm soát quyền truy cập vào các phương thức hoặc API trong
        // ứng dụng.
        System.out.println("aaaaaaaaaaa");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;

    }

}
