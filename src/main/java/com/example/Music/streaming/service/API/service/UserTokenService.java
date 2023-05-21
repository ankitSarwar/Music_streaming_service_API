package com.example.Music.streaming.service.API.service;

import com.example.Music.streaming.service.API.model.AdminAuthenticationToken;
import com.example.Music.streaming.service.API.model.UserAuthenticationToken;
import com.example.Music.streaming.service.API.model.User;
import com.example.Music.streaming.service.API.repository.IUserTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTokenService {

    @Autowired
    IUserTokenRepo tokenRepo;

    public void saveToken(UserAuthenticationToken token)
    {
        tokenRepo.save(token);
    }

    public UserAuthenticationToken getToken(User user)
    {
       return tokenRepo.findByUser(user);
    }

    public boolean authenticate(String email, String token) {
        if(token==null && email==null){
            return false;
        }
        UserAuthenticationToken authToken = tokenRepo.findFirstByToken(token);
        if(authToken==null){
            return false;
        }
        String expectedEmail = authToken.getUser().getEmail();

        return expectedEmail.equals(email);
    }

    public User findUserByToken(String token)
    {
        return tokenRepo.findFirstByToken(token).getUser();
    }

    public void deleteToken(String token) {
        UserAuthenticationToken token1 = tokenRepo.findFirstByToken(token);

        tokenRepo.deleteById(token1.getTokenId());
    }
}
