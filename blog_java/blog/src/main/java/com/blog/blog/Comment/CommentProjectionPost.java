package com.blog.blog.Comment;

import java.time.LocalDateTime;

public interface CommentProjectionPost {
    Long getIdComment();;

    String getContentComment();

    LocalDateTime getDateComment();
}
