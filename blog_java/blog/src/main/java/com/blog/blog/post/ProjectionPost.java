package com.blog.blog.post;

import java.time.LocalDateTime;

//ta có thể trải phẳng trong 1 object như dưới với 
// @Query("SELECT e.id AS id, e.heading AS heading, e.avatar AS avatar, e.content AS content, e.dateCreated AS dateCreated, e.user.full_name AS full_name, e.user.email AS email, e.user.profile_picture AS profile_picture FROM Post e")
// List<ProjectionPost> findAllPostAndUserInfo();

// public interface ProjectionPost {
//     Long getId();

//     String getHeading();

//     String getAvatar();

//     String getContent();

//     LocalDateTime getDateCreated();

//     String getFull_name();

//     String getEmail();

//     String getProfile_picture();
// }

//hoặc lồng object vào nhau như này

/*
 * {
    "id": 1,
    "content": "In this article, Joas Pambou builds the tool to provide a sentiment score in real-time with enhanced user experience by providing multilingual support. You will use an OpenAI library called Whisper that transcribes audio files into text and detects the language, and Gradio, a UI framework, to establish the interface.",
    "heading": "Generating Real-Time Audio Sentiment Analysis With AI",
    "avatar": "https://files.smashing.media/authors/hannah-milan-200px.jpg",
    "user": {
        "email": "HannahMilan@gmail.com",
        "full_name": "HannahMilan",
        "profile_picture": "https://files.smashing.media/authors/hannah-milan-200px.jpg"
    },
    "dateCreated": "2023-09-05T15:48:12.569431"
}
 */
public interface ProjectionPost {
    // dùng jpql query thì phải dùng method với tên field entity
    Long getId();

    String getHeading();

    String getAvatar();

    String getContent();

    LocalDateTime getDateCreated();

    UserProjection getUser(); // cái này ánh xạ xuống các trường bên dưới

    interface UserProjection {
        Long getId();

        String getFull_name();

        String getEmail();

        String getProfile_picture();
    }
}