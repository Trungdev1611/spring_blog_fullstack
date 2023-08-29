package com.blog.blog.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.blog.auth.User;
import com.blog.blog.auth.UserRepository;
import com.blog.blog.exception.ResourceNotFoundEx;

//khi implements UserDetailsService Spring sẽ tự nhận biết CustomUserDetailsService này thay cho UserDetailsService

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ghi đè phương thức loadUserByUsername của lớp UserDetailsService để load data
    // từ database
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("vao day login " + username);
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundEx());
        System.out.println("user " + user.getUsername() + user.getPassword());
        // lấy các quyền của user tìm được
        Set<GrantedAuthority> authorities = user.getRoles().stream()
                .map((role) -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toSet());

        System.out.println("11111" + user.toString());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                authorities);
    }

}
