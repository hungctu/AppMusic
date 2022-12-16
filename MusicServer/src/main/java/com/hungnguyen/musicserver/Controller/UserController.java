package com.hungnguyen.musicserver.Controller;

import com.hungnguyen.musicserver.Entity.User;
import com.hungnguyen.musicserver.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/server")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/login")
    public List<User> Login(@ModelAttribute("username") String usn,@ModelAttribute("password") String pass){
        return service.login(usn,pass);
    }

    @PostMapping("/signin")
    public String Signin(@Validated @ModelAttribute("user") User u) {
        return service.Signin(u);
    }

}
