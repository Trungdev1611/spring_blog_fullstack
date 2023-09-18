package com.blog.blog.post;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.blog.blog.Comment.CommentProjectionPost;
import com.blog.blog.Comment.CommentRepository;
import com.blog.blog.auth.AuthController;
import com.blog.blog.auth.User;
import com.blog.blog.auth.UserDTO;
import com.blog.blog.auth.UserRepository;
import com.blog.blog.exception.ResourceNotFoundEx;

@Service
public class PostImpl implements PostService { // implements chỉ sử dụng với interface, không được cho class

    private final PostRepository postRepository;
    private static final Logger logger = LoggerFactory.getLogger(PostImpl.class);
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Autowired
    public PostImpl(PostRepository postRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
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

    // @Override
    // public List<PostDTO> getAllPost() {
    // return postRepository.findAll().stream()
    // .map(this::convertPostToPostDTO)
    // .collect(Collectors.toList());
    // }

    @Override
    public List<ProjectionPost> getAllPost() {
        return postRepository.findAllPostAndUserInfo().stream().collect(Collectors.toList());
    }

    @Override
    public ProjectionPost getPostDetail(Long idPost) {
        ProjectionPost postDetail = postRepository.getPostDetailAndUserInfo(idPost)
                .orElseThrow(() -> new ResourceNotFoundEx());
        return postDetail;
    }

    @Override
    public PostDTOWithUserAndComment getPostDetailV2(Long idPost) {
        ProjectionPost postDetail = postRepository.getPostDetailAndUserInfo(idPost)
                .orElseThrow(() -> new ResourceNotFoundEx());
        // return postDetail;

        // HashMap<String, Object> user = new HashMap<>();
        // user.put("idUser", postDetail.getUser().getId());
        // user.put("fullName", postDetail.getUser().getFull_name());
        // user.put("email", postDetail.getUser().getEmail());
        // user.put("picture", postDetail.getUser().getProfile_picture());

        UserDTO user = new UserDTO(postDetail.getUser().getId(), postDetail.getUser().getFull_name(),
                postDetail.getUser().getEmail(), postDetail.getUser().getProfile_picture());

        List<CommentProjectionPost> commentList = commentRepository.getListCommentByIdPost(idPost);
        PostDTOWithUserAndComment postDTOWithUserAndComment = new PostDTOWithUserAndComment(
                postDetail.getId(),
                postDetail.getHeading(),
                postDetail.getAvatar(),
                postDetail.getContent(),
                postDetail.getDateCreated(),
                user,
                commentList,
                commentList.size()

        );
        return postDTOWithUserAndComment;
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

        if (post.getUser() != null) {
            newPostDTO.setAuthorName(post.getUser().getUsername());
            newPostDTO.setAuthorId(post.getUser().getId());
        }

        newPostDTO.setId(post.getId());
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
