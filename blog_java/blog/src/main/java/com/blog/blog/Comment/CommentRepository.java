package com.blog.blog.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    // nativequery sql
    // @Query(value = "SELECT c.id_comment AS idComment, c.content_comment AS
    // contentComment, c.date_comment AS dateComment FROM comment c WHERE c.post_id
    // = :idPost", nativeQuery = true)

    // jpql query
    @Query("SELECT " +
            "c.idComment AS idComment, c.contentComment AS contentComment, c.dateComment AS dateComment, c.user.username as userComment, c.parentCommentId as parentCommentId "
            +
            "FROM Comment c WHERE c.post.id = :idPost")
    List<CommentProjectionPost> getListCommentByIdPost(@Param("idPost") Long id);
}
