package com.blog.blog.auth;

import java.util.List;

import org.springframework.stereotype.Service;

import com.blog.blog.exception.ResourceNotFoundEx;
import com.blog.blog.post.PostRepository;

@Service
public class UserService {
    private UserRepository userRepository;
    private PostRepository postRepository;

    public UserService(UserRepository userRepository, PostRepository postRepository) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    public UserWithPostDTO getUserDetail(Long idUser) {
        User userDetail = userRepository.findById(idUser).orElseThrow(() -> new ResourceNotFoundEx());

        // get list post in user
        List<PostInUserProjection> listPostWithUserId = postRepository.findListPostWithUserId(idUser);

        UserWithPostDTO userWithPostDTO = new UserWithPostDTO(
                userDetail.getId(),
                userDetail.getFull_name(),
                userDetail.getEmail(),
                userDetail.getProfile_picture(),
                listPostWithUserId);
        return userWithPostDTO;

    }
}
