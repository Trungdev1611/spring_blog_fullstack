package com.blog.blog.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    // @Query(value = "SELECT u.id AS id, u.full_name AS full_name, u.email AS " +
    // " email, u.profile_picture AS picture, p.id AS id, p.heading AS heading, " +
    // " p.avatar AS avartar, p.content AS content, p.date_created AS dateCreate
    // FROM " +
    // "user u INNER JOIN post_detail p ON u.id = p.user.id WHERE u.id = :idUser",
    // nativeQuery = true)

    // @Query("SELECT u.id AS id, u.full_name AS fullName, u.email AS email,
    // u.profile_picture AS picture, u.id AS userId, p.id AS postId, p.heading AS
    // heading, p.avatar AS avatar, p.content AS content, p.dateCreated AS
    // dateCreated FROM User u LEFT JOIN FETCH u.posts p WHERE u.id = :idUser")
    @Query("SELECT u.id AS userId, u.full_name AS fullName, u.email AS email, u.profile_picture AS picture, p.id AS postId, p.heading AS heading, p.avatar AS avatar, p.content AS content, p.dateCreated AS dateCreated FROM User u LEFT JOIN  u.posts p WHERE u.id = :idUser")

    UserWithPostsProjection findUserWithPostById(@Param("idUser") Long id);

    Optional<User> findById(Long userId);

    @Query("select new com.blog.blog.auth.UserWithPostDTO (u.id, u.full_name, u.email, u.profile_picture, u.posts) from User u Where u.id =:idPost")
    UserWithPostDTO findUserWithListPostByUserId(@Param("idPost") Long id);
}
