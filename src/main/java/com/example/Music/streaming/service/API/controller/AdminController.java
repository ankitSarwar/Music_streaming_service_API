package com.example.Music.streaming.service.API.controller;

import com.example.Music.streaming.service.API.dto.SignInInput;
import com.example.Music.streaming.service.API.dto.SignInOutput;
import com.example.Music.streaming.service.API.dto.SignUpOutput;
import com.example.Music.streaming.service.API.model.Admin;
import com.example.Music.streaming.service.API.model.Song;
import com.example.Music.streaming.service.API.service.AdminService;
import com.example.Music.streaming.service.API.service.AdminTokenService;
import com.example.Music.streaming.service.API.service.SongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/Admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    SongService songService;

    @Autowired
    AdminTokenService authService;


    @PostMapping("/signup")
    public SignUpOutput signUp(@Valid @RequestBody Admin signUpDto)
    {
        return adminService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@Valid @RequestBody SignInInput signInDto)
    {
        return adminService.signIn(signInDto);
    }


    // Other admin-related CRUD operations and endpoints

    @GetMapping("/getAllAdmin")
    public ResponseEntity<List<Admin>> getAllAdmins() {
        List<Admin> admins = adminService.getAllAdmins();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/getAdmin/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Long adminId) {
        Admin admin = adminService.getAdminById(adminId);
        return ResponseEntity.ok(admin);
    }

    @PutMapping("/update/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Long adminId, @Valid @RequestBody Admin updatedAdmin) {
        Admin admin = adminService.updateAdmin(adminId, updatedAdmin);
        return ResponseEntity.ok(admin);
    }

    @DeleteMapping("/signout")
    public ResponseEntity<String> signOut(@RequestParam String email , @RequestParam String token){
        HttpStatus status;
        String msg=null;

        if(authService.authenticate(email,token))
        {
            authService.deleteToken(token);
            msg = "Signout Successful";
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid Admin";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    // Admin CRUD operations for songs

    @PostMapping("/songs")
    public ResponseEntity<String> createSong(@Valid @RequestParam String email , @RequestParam String token, @Valid @RequestBody Song song) {

        HttpStatus status;
        String msg=null;

        if(authService.authenticate(email,token))
        {
            try{
                Song createdSong = songService.createSong(song);
                status = HttpStatus.OK;
                msg = "song post sucessfully";
            }catch (Exception e){
                msg = "Enter valid information";
                status = HttpStatus.BAD_REQUEST;
            }

        }
        else
        {
            msg = "Invalid Admin";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }


    @GetMapping("/songs")
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.getAllSongs();
        return ResponseEntity.ok(songs);
    }

    @GetMapping("/songs/{songId}")
    public ResponseEntity<Song> getSongById(@PathVariable Long songId) {
        Song song = songService.getSongById(songId);
        return ResponseEntity.ok(song);
    }

    @PutMapping("/songs/{songId}")
    public Song updateSong(@PathVariable Long songId, @Valid @RequestBody Song updatedSong) {

        HttpStatus status;
        String msg=null;

        return songService.updateSong(songId, updatedSong);
    }

    @DeleteMapping("/songs/{songId}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long songId) {
        songService.deleteSong(songId);
        return ResponseEntity.noContent().build();
    }


}