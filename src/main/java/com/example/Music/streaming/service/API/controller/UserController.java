package com.example.Music.streaming.service.API.controller;

import com.example.Music.streaming.service.API.dto.SignInInput;
import com.example.Music.streaming.service.API.dto.SignInOutput;
import com.example.Music.streaming.service.API.dto.SignUpOutput;
import com.example.Music.streaming.service.API.model.Playlist;
import com.example.Music.streaming.service.API.model.Song;
import com.example.Music.streaming.service.API.model.User;
import com.example.Music.streaming.service.API.model.UserAuthenticationToken;
import com.example.Music.streaming.service.API.service.PlaylistService;
import com.example.Music.streaming.service.API.service.SongService;
import com.example.Music.streaming.service.API.service.UserService;
import com.example.Music.streaming.service.API.service.UserTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/User")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PlaylistService playlistService;

    @Autowired
    SongService songService;

    @Autowired
    UserTokenService authService;

    // User CRUD Operations
    @PostMapping("/signup")
    public SignUpOutput signUp(@Valid @RequestBody User signUpDto)
    {
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@Valid @RequestBody SignInInput signInDto)
    {
        return userService.signIn(signInDto);
    }


    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @GetMapping("/getAllUser")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}")
    public User updateUser(@PathVariable Long userId, @Valid @RequestBody User user) {
        return userService.updateUser(userId, user);
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


    @GetMapping("/songs")
    public ResponseEntity<List<Song>> getAllSongs(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false)Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = "5",required = false)Integer pageSize
    ) {
        List<Song> songs = songService.getAllSongs(pageNumber,pageSize);
        return ResponseEntity.ok(songs);
    }


    // Add song to user playlist
   @PostMapping("/addPlayList")
   public Playlist addPlayList(@RequestBody Playlist playlist){
        return playlistService.createPlaylist(playlist);
   }

    @GetMapping("/getPlayList")
    public List<Playlist> getAll(){
        return playlistService.getAllPlaylists();
    }

    @GetMapping("/getById/{id}")
    public Playlist getPlaylistById(@PathVariable Long id){
        return  playlistService.getPlaylistById(id);
    }

    @PutMapping("/updatePlayList/{id}")
    public Playlist updatePlaylist(@PathVariable Long id,@RequestBody Playlist playlist){
        return playlistService.updatePlaylist(id,playlist);
    }

    @DeleteMapping("/deletePlaylist/{id}")
    public void deletePlaylist(@PathVariable Long id){
        playlistService.deletePlaylist(id);
    }

}