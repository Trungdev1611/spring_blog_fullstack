package com.blog.blog.auth;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

import com.blog.blog.Role.Role;
import com.blog.blog.Role.RoleRepository;
import com.blog.blog.exception.ApiException;
import com.blog.blog.jwt.JwtProvider;

@Service
public class AuthServiceImpl implements AuthService {

    public AuthenticationManager authenticationManager;
    public JwtProvider jwtTokenProvider;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
            JwtProvider jwtTokenProvider, UserRepository userRepository, RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String login(LoginDTO loginDTO) {

        // set body từ request vào authenticcationManager
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                loginDTO.getUsername(), loginDTO.getPassword());

        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // đặt thông tin xác thực vào context bảo mật của Spring Security, để ứng dụng
        // biết rằng người dùng đã xác thực thành công.
        // Chẳng hạn, có thể sử dụng annotations như @Secured, @PreAuthorize,
        // @PostAuthorize để kiểm soát quyền truy cập vào các phương thức hoặc API trong
        // ứng dụng.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtTokenProvider.generateToken(authentication);

        return token;

    }

    public String register(RegisterDTO registerDTO) {

        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            throw new ApiException("Username already exits in database");
        }

        User user = new User();
        user.setUsername((registerDTO.getUsername()));
        user.setEmail((registerDTO.getEmail()));

        String passwordHash = passwordEncoder.encode(registerDTO.getPassword());
        user.setPassword(passwordHash);

        user.setFull_name((registerDTO.getFullName()));

        Role roleUser = roleRepository.findByRoleName("ROLE_USER").orElseThrow(
                () -> new ApiException("Không tồn tại ROLE_USER trong database"));

        Set<Role> rolesDefault = new HashSet<>();
        rolesDefault.add(roleUser);

        user.setRoles(rolesDefault);
        userRepository.save(user);
        return "Register success";
    }

}
