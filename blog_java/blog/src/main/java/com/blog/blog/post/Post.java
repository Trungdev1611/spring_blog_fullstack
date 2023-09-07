package com.blog.blog.post;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.blog.blog.auth.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
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

@Table(name = "post_detail")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String heading;
    private String avatar;
    @Column(columnDefinition = "LONGTEXT ")
    private String content;// nếu không dùng columnDefinition sẽ mặc định varchar với
                           // vài ngàn kí tự

    @CreationTimestamp // tự động tạo thời gian trong database
    @Column(name = "date_created")
    private LocalDateTime dateCreated;

    // nhiều post thuộc 1 user
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @NotNull(message = "user_id không được để trống hoặc null")
    private User user;

}
