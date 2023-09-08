package com.blog.blog.auth;

import java.time.LocalDateTime;
import java.util.List;

public interface UserWithPostsProjection {
    // dùng native query thì phải dùng method với tên bảng
    Long getId();

    String getFull_name();

    String getEmail();

    String getProfile_picture();

    List<PostProjection> getPost();

    interface PostProjection {
        Long getId();

        String getHeading();

        String getAvatar();

        String getContent();

        LocalDateTime getDate_Created();
    }
}
