package com.blog.blog.post;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<ProjectionPost> getAllPost();

    ProjectionPost getPostDetail(Long idPost);

    PostDTOWithUserAndComment getPostDetailV2(Long idPost);

    PostDTO updatePost(PostDTO postDTO, Long idPost);

    void deletePost(Long idPost);
}
