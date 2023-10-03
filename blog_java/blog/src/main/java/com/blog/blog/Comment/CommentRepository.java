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
        // @Query(value = "SELECT " +
        // " c.idComment AS idComment, " +
        // " c.contentComment AS contentComment, " +
        // " c.dateComment AS dateComment, " +
        // " c.user.username AS userComment, " +
        // " c.user.profile_picture AS userImg, " +
        // " c.replies AS replies, " +
        // " r.idReply AS idReply, " +
        // " r.contentReply AS contentReply, " +
        // " r.dateComment AS dateReply " +
        // "FROM Comment c " +
        // " LEFT JOIN c.replies r " +
        // "WHERE c.post.id = :idPost " +
        // "ORDER BY c.idComment DESC ", countQuery = "SELECT count(*) FROM Comment c
        // WHERE c.post.id = :idPost")

        @Query(value = "SELECT " +
                        "    c.id_comment AS idComment, " +
                        "    c.content_comment AS contentComment, " +
                        "    c.date_comment AS dateComment, " +
                        "    u.username AS userComment, " +
                        "    u.profile_picture AS userImg, " +
                        // " c.replies AS replies, " +
                        "    r.id_reply AS idReply, " +
                        "    r.content_reply AS contentReply, " +
                        "    r.date_comment AS dateReply " +
                        "FROM comment c " +
                        "LEFT JOIN reply r ON c.id_comment = r.comment_id " +
                        "LEFT JOIN user u ON c.user_id = u.id " +
                        "WHERE c.post_id = :idPost "
        // +
        // "ORDER BY c.id_comment DESC"
                        , countQuery = "SELECT COUNT(DISTINCT c.id_comment) FROM comment c ", nativeQuery = true)
        Page<CommentProjectionPost> getListCommentByIdPost(@Param("idPost") Long id, Pageable pageable);

        @Query(value = "SELECT " +
                        "  c "
                        +
                        "  FROM Comment c " +
                        "  WHERE c.post.id = :idPost " +
                        "  ORDER BY c.dateComment ASC")

        List<Test> getListCommentByIdPostTest(@Param("idPost") Long id);

        @Query(value = " SELECT " +
                        " count (*) FROM Comment c " +
                        " WHERE c.post.id = :idPost ")
        Long totalCountCommentByPostId(@Param("idPost") Long id);

}
