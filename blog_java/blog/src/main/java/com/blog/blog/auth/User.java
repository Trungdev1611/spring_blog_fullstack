package com.blog.blog.auth;

import java.util.Set;

import com.blog.blog.Role.Role;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity

@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    private String full_name;

    private String email;

    // thiết lập bảng chung giữa table User và Role
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // EAGER nghĩa là khi load user thì cũng load luôn
                                                                    // role của nó
    @JoinTable(name = "role_user",
            // có cột user_id có giá trị tham chiếu cột id trong table User
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            // có cột role_id có giá trị tham chiếu cột id trong table Role
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}