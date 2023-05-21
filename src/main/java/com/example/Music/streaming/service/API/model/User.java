package com.example.Music.streaming.service.API.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;


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
    @Size(min=10,max=12)
    @Pattern(regexp = "^[0-9]+$")
    private String userContact;


    public User(String username, String password, String email, String userContact) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.userContact = userContact;
    }

}