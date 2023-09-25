package com.blog.blog.Comment;

public interface CommentService {
    CommentDTO createComment(Comment comment, Long idPost);

    Object getCommentByIdPost(Long postId);
}
