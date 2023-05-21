package com.example.Music.streaming.service.API.service;

import com.example.Music.streaming.service.API.dto.SignInInput;
import com.example.Music.streaming.service.API.dto.SignInOutput;
import com.example.Music.streaming.service.API.dto.SignUpOutput;
import com.example.Music.streaming.service.API.model.Playlist;
import com.example.Music.streaming.service.API.model.UserAuthenticationToken;
import com.example.Music.streaming.service.API.model.User;
import com.example.Music.streaming.service.API.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    IUserRepository userRepository;

    @Autowired
    UserTokenService tokenService;


    public SignUpOutput signUp(User signUpDto) {

        //check if user exists or not based on email
        User user = userRepository.findFirstByEmail(signUpDto.getEmail());

        if(user != null)
        {
            throw new IllegalStateException("User already exists!!!!...sign in instead");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        signUpDto.setPassword(encryptedPassword);
        userRepository.save(signUpDto);

        return new SignUpOutput("User registered","Music streaming account created successfully");

    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;

    }

    public SignInOutput signIn(SignInInput signInDto) {
        //check if user exists or not based on email
        User user = userRepository.findFirstByEmail(signInDto.getUserEmail());

        if(user == null)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getUserPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        UserAuthenticationToken token = new UserAuthenticationToken(user);

        tokenService.saveToken(token);

        //set up output response

        return new SignInOutput("Authentication Successfull !!!", token.getToken());

    }



    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("User not found with ID: " + userId));
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }

    public User updateUser(Long userId, User updatedUser) {
        User user = getUserById(userId);
        user.setUsername(updatedUser.getUsername());
        user.setUserContact(updatedUser.getUserContact());
        return userRepository.save(user);
    }

    public void deleteUser(Long userId)
    {
        userRepository.deleteById(userId);
    }



}
