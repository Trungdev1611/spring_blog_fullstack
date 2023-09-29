package com.blog.blog.Comment;

import java.util.List;

import org.springframework.data.domain.Page;

import com.blog.blog.Response.Paginate;

public interface CommentService {
    CommentDTO createComment(Comment comment, Long idPost);

    // List<CommentProjectionPost> getCommentByIdPost(Long postId, int pageIndex,
    // int pageSize);
    Paginate<List<CommentProjectionPost>> getCommentByIdPost(Long postId, int pageIndex, int pageSize);

    List<Test> getCommentByIdPostTest(Long postId, int pageIndex, int pageSize);

}
