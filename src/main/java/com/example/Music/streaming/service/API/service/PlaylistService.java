package com.example.Music.streaming.service.API.service;

import com.example.Music.streaming.service.API.model.Playlist;
import com.example.Music.streaming.service.API.repository.IPlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

    @Autowired
    IPlaylistRepository playlistRepository;


    public PlaylistService(IPlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    public Playlist createPlaylist(Playlist playlist) {
        return playlistRepository.save(playlist);
    }

    public List<Playlist> getAllPlaylists() {
        return playlistRepository.findAll();
    }

    public Playlist getPlaylistById(Long playlistId) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        if (optionalPlaylist.isPresent()) {
            return optionalPlaylist.get();
        }
        throw new IllegalArgumentException("Playlist not found with ID: " + playlistId);
    }

    public Playlist updatePlaylist(Long playlistId, Playlist updatedPlaylist) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        if (optionalPlaylist.isPresent()) {
            Playlist playlist = optionalPlaylist.get();
            playlist.setName(updatedPlaylist.getName());
            playlist.setSongs(updatedPlaylist.getSongs());
            return playlistRepository.save(playlist);
        }
        throw new IllegalArgumentException("Playlist not found with ID: " + playlistId);
    }

    public void deletePlaylist(Long playlistId) {
        Optional<Playlist> optionalPlaylist = playlistRepository.findById(playlistId);
        if (optionalPlaylist.isPresent()) {
            playlistRepository.delete(optionalPlaylist.get());
        } else {
            throw new IllegalArgumentException("Playlist not found with ID: " + playlistId);
        }
    }





}