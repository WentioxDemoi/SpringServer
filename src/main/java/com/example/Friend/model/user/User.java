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
    public User(String Username, String Email, String Password, String Role) {

        this.username = Username;
        this.email = Email;
        this.password = Password;
        this.role = Role;
        this.role = Role;
    }
}