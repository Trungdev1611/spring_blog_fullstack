package com.blog.blog.Comment;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentRepository extends JpaRepository<Comment, Long> {
        // nativequery sql
        // @Query(value = "SELECT c.id_comment AS idComment, c.content_comment AS
        // contentComment, c.date_comment AS dateComment FROM comment c WHERE c.post_id
        // = :idPost", nativeQuery = true)

        // jpql query
        @Query(value = "SELECT " +
                        "    c.idComment AS idComment, " +
                        "    c.contentComment AS contentComment, " +
                        "    c.dateComment AS dateComment, " +
                        "    c.user.username AS userComment, " +
                        "    c.user.profile_picture AS userImg, " +
                        "    r.idReply AS idReply, " +
                        "    r.contentReply AS contentReply, " +
                        "    r.dateComment AS dateReply " +
                        "FROM Comment c " +
                        "LEFT JOIN c.replies r " +
                        "WHERE c.post.id = :idPost " +
                        "ORDER BY c.dateComment DESC ", countQuery = "SELECT count (*) FROM Comment c WHERE c.post.id = :idPost")
        // List<CommentProjectionPost> getListCommentByIdPost(@Param("idPost") Long id);
        Page<CommentProjectionPost> getListCommentByIdPost(@Param("idPost") Long id, Pageable pageable);

        @Query(value = "SELECT " +
                        "c "
                        +
                        "FROM Comment c WHERE c.post.id = :idPost " +
                        "ORDER BY c.dateComment ASC", countQuery = "SELECT count (*) FROM Comment c WHERE c.post.id = :idPost")
        // List<CommentProjectionPost> getListCommentByIdPost(@Param("idPost") Long id);
        List<Test> getListCommentByIdPostTest(@Param("idPost") Long id);

}
