package com.example.sagar.companyproduct.model;

/**
 * Created by SAGAR on 26-04-2018.
 */

public class User
{
    private String Password;
    private String User_Type;

    User()
    {

    }
    public User(String Password,String User_Type)
    {
        this.Password = Password;
        this.User_Type = User_Type;
    }
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUser_Type() {
        return User_Type;
    }

    public void setUser_Type(String user_Type) {
        User_Type = user_Type;
    }
}
