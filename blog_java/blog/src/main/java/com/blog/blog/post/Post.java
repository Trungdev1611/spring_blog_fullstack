package com.blog.blog.post;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "post-detail")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String heading;
    private String avatar;
    private String content;

    @CreationTimestamp // tự động tạo thời gian trong database
    @Column(name = "date_created", columnDefinition = "TEXT") // nếu không dùng columnDefinition sẽ mặc định varchar với
                                                              // vài ngàn kí tự
    private LocalDateTime dateCreated;

}
