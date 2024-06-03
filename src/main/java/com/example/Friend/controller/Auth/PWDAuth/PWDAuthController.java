package com.example.Friend.controller.Auth.PWDAuth;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.Friend.controller.Auth.ErrorHandler.InvalidPasswordException;
import com.example.Friend.model.user.User;
import com.example.Friend.model.user.UserDTO;
import com.example.Friend.security.JwtTokenProvider;
import com.example.Friend.service.user.UserService;
import org.springframework.security.core.Authentication;


@RestController
@RequestMapping("/user")
@CrossOrigin
public class PWDAuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    @PostMapping("/register")
    public ResponseEntity<String> addUser(@RequestBody UserDTO user) {
        User user_ = new User(user.getUsername(), user.getEmail(), passwordEncoder.encode((user.getPassword())), user.getRole());
        userService.handleUserRegistration(user_);
        //Authentication authentication = new UsernamePasswordAuthenticationToken(user_, null, null);
        String token = jwtTokenProvider.generateToken(user_.getEmail());
        return ResponseEntity.ok(token);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDTO user) {
    User user_ = userService.handleUserLogin(new User(user.getUsername(), user.getEmail(), passwordEncoder.encode((user.getPassword())), user.getRole()));
    if (passwordEncoder.matches(user.getPassword(), user_.getPassword())) {
        //Authentication authentication = new UsernamePasswordAuthenticationToken(user_, null, null);
        String token = jwtTokenProvider.generateToken(user_.getEmail());
        return ResponseEntity.ok(token);
    } else {
        throw new InvalidPasswordException(null);
    }
}
    @PostMapping("/getusername")
    public ResponseEntity<String> getUsername(@RequestBody String token) {
        try {
            // Verify the token and extract the username
            if (jwtTokenProvider.validateToken(token) == true) {
                String username = userService.getUsernameByEmail(jwtTokenProvider.getEmailFromToken(token));
                return ResponseEntity.ok(username);
            } else {
                return ResponseEntity.status(401).body("Invalid token");
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }


    // @GetMapping("/get_users")
    // public List<User> GetUsers() {
    //     return (this.userService.GetAllUsers());
    // }


    

}

