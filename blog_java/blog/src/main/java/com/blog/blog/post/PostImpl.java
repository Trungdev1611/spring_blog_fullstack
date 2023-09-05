package com.blog.blog.post;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.blog.auth.AuthController;
import com.blog.blog.auth.User;
import com.blog.blog.auth.UserRepository;
import com.blog.blog.exception.ResourceNotFoundEx;

@Service
public class PostImpl implements PostService { // implements chỉ sử dụng với interface, không được cho class

    private final PostRepository postRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostImpl.class);
    private final UserRepository userRepository;

    @Autowired
    public PostImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("aaaa::: " + authentication.getName());

        User userDetails = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundEx());
        // convert postDTO to post
        postDTO.setUser(userDetails);

        Post post = convertPostDTOToPost(postDTO);

        Post postGetFromDB = postRepository.save(post);

        // convert post to postDTO
        return convertPostToPostDTO(postGetFromDB);
    }

    @Override
    public List<PostDTO> getAllPost() {
        return postRepository.findAll().stream()
                .map(this::convertPostToPostDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO getPostDetail(Long idPost) {
        Post postDetail = postRepository.findById(idPost).orElseThrow(() -> new ResourceNotFoundEx());
        return convertPostToPostDTO(postDetail);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long idPost) {

        Post postGetfromDb = postRepository.findById(idPost).orElseThrow(() -> new ResourceNotFoundEx());

        Post postUpdateSendFromClient = convertPostDTOToPost(postDTO);

        postGetfromDb.setAvatar(postUpdateSendFromClient.getAvatar());
        postGetfromDb.setContent(postUpdateSendFromClient.getContent());
        postGetfromDb.setHeading(postUpdateSendFromClient.getHeading());
        postRepository.save(postGetfromDb);
        return convertPostToPostDTO(postUpdateSendFromClient);
    }

    @Override
    public void deletePost(Long idPost) {
        postRepository.findById(idPost).orElseThrow(() -> new ResourceNotFoundEx());
        postRepository.deleteById(idPost);
    }

    public PostDTO convertPostToPostDTO(Post post) {
        PostDTO newPostDTO = new PostDTO();
        newPostDTO.setHeading(post.getHeading());
        newPostDTO.setContent(post.getContent());
        newPostDTO.setAvatar(post.getAvatar());
        newPostDTO.setAuthorName(post.getUser().getUsername());
        newPostDTO.setAuthorId(post.getUser().getId());
        return newPostDTO;

    }

    public Post convertPostDTOToPost(PostDTO postDTO) {
        Post post = new Post();
        post.setHeading(postDTO.getHeading());
        post.setContent(postDTO.getContent());
        post.setAvatar(postDTO.getAvatar());
        post.setUser(postDTO.getUser());
        return post;

    }

}
