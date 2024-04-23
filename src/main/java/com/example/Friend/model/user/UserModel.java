package com.example.Friend.model.user;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;


@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String Username;
    private String Email;
    private String Password;

    public UserModel(){}

    public void SetId(int Id_)
    {
        Id = Id_;
    }

    public int GetId()
    {
        return (Id);
    }
    
    public void SetUsername(String Username_)
    {
        Username = Username_;
    }

    public String GetUsername()
    {
        return (Username);
    }

    public void SetEmail(String Email_)
    {
        Email = Email_;
    }

    public String GetEmail()
    {
        return (Email);
    }

    public void SetPassword(String Password_)
    {
        Username = Password_;
    }

    public String GetPassword()
    {
        return (Password);
    }

}
