package com.example.Friend.controller.Auth.PWDAuth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Friend.model.user.User;
import com.example.Friend.model.user.UserDTO;
import com.example.Friend.service.user.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class PWDAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;



    @PostMapping("/register")
    @ResponseStatus(HttpStatus.OK)
    public void addUser(@RequestBody UserDTO user) {
        this.userService.handleUserRegistration(new User(user.Username, user.Email, passwordEncoder.encode(user.Password), user.Role));
    }

    // @GetMapping("/get_users")
    // public List<User> GetUsers() {
    //     return (this.userService.GetAllUsers());
    // }

    

}
