package com.blog.blog.post;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // @Query(value = "SELECT post_detail.id, post_detail.avatar,
    // post_detail.content, post_detail.date_created, post_detail.heading,
    // post_detail.user_id, user.full_name, user.email FROM post_detail INNER JOIN
    // user ON post_detail.user_id=user.id", nativeQuery = true)

    // @Query("select new com.blog.blog.post.PostDTOTest(e.id, e.heading, e.avatar,
    // e.content, e.dateCreated, e.user.full_name, e.user.email) from Post e")
    // List<PostDTOTest> findAllPostAndUserInfo();

    @Query("select new com.blog.blog.post.PostDTOTest(e.id, e.heading, e.avatar, e.content, e.dateCreated, e.user.full_name, e.user.email) from Post e")
    List<ProjectionPost> findAllPostAndUserInfo();
}
