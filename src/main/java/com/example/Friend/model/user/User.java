package com.example.Friend.model.user;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import jakarta.persistence.GenerationType;
import jakarta.persistence.*;


@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "Username", nullable = false, length = 64)
    private String Username;

    @Column(name = "Email")
    private String Email;

    @Column(name = "Password")
    private String Password;

    public User(String Username, String Password, String Email) {

        this.Username = Username;
        this.Password = Password;
        this.Email = Email;

    }
}