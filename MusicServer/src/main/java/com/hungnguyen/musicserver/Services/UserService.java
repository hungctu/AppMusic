package com.hungnguyen.musicserver.Services;

import com.hungnguyen.musicserver.Entity.User;
import com.hungnguyen.musicserver.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository user;
    public static String getMd5(String input)
    {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String Signin(User u) {
        String success = "";
        String passString = getMd5(u.getPassword());
        User u1 = new User(u.getFullname(), u.getUsername(), passString);
        if (user.kiemtra(u.getUsername()).size()>0) {
            success = "DaTonTai";
        } else {
            if(ResponseEntity.ok(user.save(u1)).hasBody()){
                success = "success";
            }else {

            }
        }
        return "[{\"success\":\"" + success + "\"}]";
    }

    public List<User> login(String usn,String pass){
        return user.login(usn,pass);
    }
}
