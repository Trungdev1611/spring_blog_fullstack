package com.blog.blog.Comment;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentProjectionPost {
    Long getIdComment();;

    String getContentComment();

    LocalDateTime getDateComment();

    String getUserComment();

    Long getParentCommentId();

    String getUserImg();

    // List<ReplyContent> getReplies();
    Long getIdReply();

    String getContentReply();

    LocalDateTime getDateReply();

}

interface ReplyContent {
    Long getIdReply();

    String getContentReply();

    LocalDateTime getDateReply();
}