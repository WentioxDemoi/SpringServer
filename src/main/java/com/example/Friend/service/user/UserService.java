package com.example.Friend.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Friend.model.user.User;
import com.example.Friend.model.user.UserDTO;
import com.example.Friend.repository.user.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void AddUser(UserDTO userDTO) {
        User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getEmail());
        userRepository.save(user);
    }

}
