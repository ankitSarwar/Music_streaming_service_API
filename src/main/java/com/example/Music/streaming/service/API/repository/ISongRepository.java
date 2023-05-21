package com.example.Music.streaming.service.API.repository;

import com.example.Music.streaming.service.API.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISongRepository extends JpaRepository<Song,Long> {
}
