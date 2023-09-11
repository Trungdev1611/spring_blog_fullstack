package com.blog.blog.auth;

import java.time.LocalDateTime;

public interface PostInUserProjection {

    Long getIdPost();

    String getHeading();

    String getAvatar();

    String getContent();

    LocalDateTime getDateCreated();
}
