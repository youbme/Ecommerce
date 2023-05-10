package com.example.ecomfinal.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class AdminUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column (name = "first_name")
    private String firstname;

    @Column(name = "last_name")
    private String lastname;

    @Column(name = "admin_type")
    private String admin_type;

    @Column(name = "lastlogin")
    private String lastlogin;

    @Column(name = "created_at")
    private LocalDateTime createdat;

    @Column(name = "modified_at")
    private LocalDateTime modifiedat;
}
