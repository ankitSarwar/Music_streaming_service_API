package com.example.Music.streaming.service.API.repository;

import com.example.Music.streaming.service.API.model.Admin;
import com.example.Music.streaming.service.API.model.AdminAuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminTokenRepo extends JpaRepository<AdminAuthenticationToken , Long> {

    AdminAuthenticationToken findFirstByToken(String token);

    AdminAuthenticationToken findByAdmin(Admin admin);
}
