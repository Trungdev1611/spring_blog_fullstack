package com.blog.blog.post;

import java.time.LocalDateTime;

public interface ProjectionPost {
    Long getId();

    String getHeading();

    String getAvatar();

    String getContent();

    LocalDateTime getDateCreated();

    String getFull_name();

    String getEmail();
}
