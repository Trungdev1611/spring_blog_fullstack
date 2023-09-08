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

    // @Query("SELECT e.id AS id, e.heading AS heading, e.avatar AS avatar,
    // e.content AS content, e.dateCreated AS dateCreated, e.user.full_name AS
    // full_name, e.user.email AS email, e.user.profile_picture AS profile_picture
    // FROM Post e")
    // List<ProjectionPost> findAllPostAndUserInfo();

    // @Query bên dưới là JPQL, có thể viết native query cũng ok,
    // Lưu ý khi sử dụng JPQL thì FROM Post với Post là tên entity, các method
    // interface trong projection là tên field trong entity
    // còn trong native query thì Post là tên bảng, các method interface trong
    // projection là tên cột
    @Query("SELECT e.id AS id, e.heading AS heading, e.avatar AS avatar, e.content AS content, e.dateCreated AS dateCreated, e.user AS user FROM Post e")
    List<ProjectionPost> findAllPostAndUserInfo();

}
