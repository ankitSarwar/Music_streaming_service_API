package com.example.Music.streaming.service.API.service;

import com.example.Music.streaming.service.API.model.Admin;
import com.example.Music.streaming.service.API.model.AdminAuthenticationToken;
import com.example.Music.streaming.service.API.repository.IAdminTokenRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminTokenService {

    @Autowired
    IAdminTokenRepo tokenRepo;

    public void saveToken(AdminAuthenticationToken token) {
        tokenRepo.save(token);
    }

    public AdminAuthenticationToken getToken(Admin admin){
       return tokenRepo.findByAdmin(admin);
    }

    public boolean authenticate(String email, String token) {

        if(token==null && email==null){
            return false;
        }

        AdminAuthenticationToken authToken = tokenRepo.findFirstByToken(token);

        if(authToken==null){
            return false;
        }

        String expectedEmail = authToken.getAdmin().getEmail();

        return expectedEmail.equals(email);
    }


    public Admin findUserByToken(String token)
    {
        return tokenRepo.findFirstByToken(token).getAdmin();
    }

    public void deleteToken(String token) {
        AdminAuthenticationToken token1 = tokenRepo.findFirstByToken(token);

        tokenRepo.deleteById(token1.getTokenId());
    }
}
