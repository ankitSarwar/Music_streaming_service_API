package com.example.Music.streaming.service.API.service;

import com.example.Music.streaming.service.API.dto.SignInInput;
import com.example.Music.streaming.service.API.dto.SignInOutput;
import com.example.Music.streaming.service.API.dto.SignUpInput;
import com.example.Music.streaming.service.API.dto.SignUpOutput;
import com.example.Music.streaming.service.API.model.Admin;
import com.example.Music.streaming.service.API.model.AdminAuthenticationToken;
import com.example.Music.streaming.service.API.model.User;
import com.example.Music.streaming.service.API.model.UserAuthenticationToken;
import com.example.Music.streaming.service.API.repository.IAdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    IAdminRepository adminRepository;

    @Autowired
    AdminTokenService adminTokenService;

    public SignUpOutput signUp(Admin signUpDto) {

        //check if user exists or not based on email
        Admin admin = adminRepository.findFirstByEmail(signUpDto.getEmail());

        if(admin != null)
        {
            throw new IllegalStateException("Admin already exists!!!!...sign in instead");
        }

//      encryption
        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        signUpDto.setPassword(encryptedPassword);
        adminRepository.save(signUpDto);

        return new SignUpOutput("Admin registered","Music streaming account created successfully");

    }

    private String encryptPassword(String adminPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(adminPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;

    }

    public SignInOutput signIn(SignInInput signInDto) {
        //check if user exists or not based on email
        Admin admin = adminRepository.findFirstByEmail(signInDto.getUserEmail());

        if(admin == null)
        {
            throw new IllegalStateException("Admin invalid!!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getUserPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(admin.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("Admin invalid!!!!...sign up instead");
        }

        AdminAuthenticationToken token = new AdminAuthenticationToken(admin);

        adminTokenService.saveToken(token);

        //set up output response

        return new SignInOutput("Authentication Successfull !!!", token.getToken());

    }


    public Admin getAdminById(Long adminId) {
        return adminRepository.findById(adminId)
                .orElseThrow(() -> new IllegalStateException("Admin not found with ID: " + adminId));
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin updateAdmin(Long adminId, Admin updatedAdmin) {
        Admin admin = getAdminById(adminId);
        admin.setAdminName(updatedAdmin.getAdminName());
        admin.setAdminContact(updatedAdmin.getAdminContact());
        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long adminId)
    {
        adminRepository.deleteById(adminId);
    }

}
