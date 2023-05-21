package com.example.Music.streaming.service.API.service;

import com.example.Music.streaming.service.API.model.Song;
import com.example.Music.streaming.service.API.repository.ISongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    ISongRepository songRepository;

    public Song createSong(Song song) {
        return songRepository.save(song);
    }

    public List<Song> getAllSongs() {
        return songRepository.findAll();
    }

    public Song getSongById(Long songId) {
        Optional<Song> optionalSong = songRepository.findById(songId);
        if (optionalSong.isPresent()) {
            return optionalSong.get();
        }
        throw new IllegalArgumentException("Song not found with ID: " + songId);
    }

    public Song updateSong(Long songId, Song updatedSong) {
        Optional<Song> optionalSong = songRepository.findById(songId);
        if (optionalSong.isPresent()) {
            Song song = optionalSong.get();
            song.setTitle(updatedSong.getTitle());
            song.setArtist(updatedSong.getArtist());
            song.setGenre(updatedSong.getGenre());
            song.setDuration(updatedSong.getDuration());
            return songRepository.save(song);
        }
        throw new IllegalArgumentException("Song not found with ID: " + songId);
    }

    public void deleteSong(Long songId) {
        Optional<Song> optionalSong = songRepository.findById(songId);
        if (optionalSong.isPresent()) {
            songRepository.delete(optionalSong.get());
        } else {
            throw new IllegalArgumentException("Song not found with ID: " + songId);
        }
    }
}