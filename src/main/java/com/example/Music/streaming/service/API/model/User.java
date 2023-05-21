package com.example.Music.streaming.service.API.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    private String username;
    @NotNull
    private String password;

    @NotEmpty
    @Email
    private String email;

    @NotEmpty
    private String userContact;


    public User(String username, String password, String email, String userContact) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userContact = userContact;
    }

}