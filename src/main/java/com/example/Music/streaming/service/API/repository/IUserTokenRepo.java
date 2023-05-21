package com.example.Music.streaming.service.API.repository;

import com.example.Music.streaming.service.API.model.UserAuthenticationToken;
import com.example.Music.streaming.service.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserTokenRepo extends JpaRepository<UserAuthenticationToken, Long> {

    UserAuthenticationToken findByUser(User user);

    UserAuthenticationToken findFirstByToken(String token);

}