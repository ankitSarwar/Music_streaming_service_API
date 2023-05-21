package com.example.Music.streaming.service.API.repository;

import com.example.Music.streaming.service.API.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdminRepository extends JpaRepository<Admin,Long> {

    Admin findFirstByEmail(String email);
}
