package com.example.Friend.model.user;
import lombok.Data;
import jakarta.persistence.*;


@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;
    private String password;
    private String role; //Eg: ADMIN,USER

    public User() {}
    public User(String Username, String Password, String Email, String Role) {

        this.username = Username;
        this.password = Password;
        this.email = Email;
        this.role = Role;


    }
}