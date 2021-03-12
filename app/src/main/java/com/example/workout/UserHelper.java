package com.example.workout;

public class UserHelper {
    public String email;
    public String username;



    public String password;

    public UserHelper(){
        
    }

    public UserHelper(String email, String username, String password) {
        this.username=username;
        this.email = email;
        this.password=password;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
