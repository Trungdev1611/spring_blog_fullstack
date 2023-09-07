package com.blog.blog.post;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    List<?> getAllPost();

    Post getPostDetail(Long idPost);

    PostDTO updatePost(PostDTO postDTO, Long idPost);

    void deletePost(Long idPost);
}
