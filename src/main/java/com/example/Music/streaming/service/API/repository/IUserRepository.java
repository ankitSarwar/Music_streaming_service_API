package com.example.Music.streaming.service.API.repository;

import com.example.Music.streaming.service.API.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<User,Long> {
    User findFirstByEmail(String email);

    @Modifying
    @Query("DELETE FROM User u WHERE u.userId = :userId")
    void deleteUserById(Long userId);
}
