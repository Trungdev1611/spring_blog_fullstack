package com.blog.blog.auth;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    @Query(value = "SELECT u.id AS id, u.full_name AS full_name, u.email AS email, u.profile_picture AS picture, p.id AS id, p.heading AS heading, p.avatar AS avartar, p.content AS content, p.date_created AS dateCreate FROM user u INNER JOIN post_detail p ON u.id = p.user_id WHERE u.id = :idUser", nativeQuery = true)
    UserWithPostsProjection findUserWithPostById(@Param("idUser") Long id);
}
