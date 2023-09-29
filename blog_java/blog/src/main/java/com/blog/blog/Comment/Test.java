package com.blog.blog.Comment;

import java.time.LocalDateTime;
import java.util.List;

public interface Test {
    Long getIdComment();;

    String getContentComment();

    LocalDateTime getDateComment();

    // String getUserComment();

    // Long getParentCommentId();

    // String getUserImg();

    List<ReplyContent> getReplies();

}

interface ReplyContent {
    Long getIdReply();

    String getContentReply();
}