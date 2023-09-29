package com.blog.blog.Comment;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idReply;

    private String contentReply;

    @CreationTimestamp
    private LocalDateTime dateComment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "comment_id")
    private Comment comment;
}
