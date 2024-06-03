package com.example.Friend.service.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.Friend.controller.Auth.ErrorHandler.EmailAlreadyExistsException;
import com.example.Friend.controller.Auth.ErrorHandler.EmailNotFoundException;
import com.example.Friend.controller.Auth.ErrorHandler.UsernameAlreadyExistsException;
import com.example.Friend.model.user.User;
import com.example.Friend.model.user.UserDTO;
import com.example.Friend.repository.user.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        if (user.isPresent()) {
            var userObj = user.get();
            return org.springframework.security.core.userdetails.User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        } else {
            throw new UsernameNotFoundException(username);
        }
    }

    public User handleUserRegistration(User user) {
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException(user.getEmail());
        }
        if (repository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException(user.getUsername());
        }
        return repository.save(user);
    }

    public User handleUserLogin(User user) {
        
        User user_ = repository.findByEmail(user.getEmail())
        .orElseThrow(() -> new EmailNotFoundException(user.getEmail()));

        return user_;
    }

    public String getUsernameByEmail(String email)
    {
        User user_ = repository.findByEmail(email)
        .orElseThrow(() -> new EmailNotFoundException(email));

        return user_.getUsername();

    }


    private String[] getRoles(User user) {
        if (user.getRole() == null) {
            return new String[]{"USER"};
        }
        return user.getRole().split(",");
    }

}
