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
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long adminId;

    @NotNull
    private String adminName;
    @NotNull
    private String password;

    @NotEmpty
    @Size(min=10,max=12)
    @Pattern(regexp = "^[0-9]+$")
    private String adminContact;

    @Column(unique = true, nullable = false)
    @Email
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@admin\\.com$")
    private String email;


    public Admin(String adminname, String password, String email, String adminContact) {
        this.adminName = adminname;
        this.password = password;
        this.email = email;
        this.adminContact = adminContact;
    }





}
