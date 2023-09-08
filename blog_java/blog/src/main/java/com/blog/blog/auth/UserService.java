package com.blog.blog.auth;

import org.springframework.stereotype.Service;

import com.blog.blog.exception.ResourceNotFoundEx;

@Service
public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserWithPostsProjection getUserDetail(Long idUser) {
        return userRepository.findUserWithPostById(idUser);

    }
}
