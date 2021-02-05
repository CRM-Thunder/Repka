package com.example.workout;

public class UserHelper {
    public String email, username;

    public UserHelper(){
        
    }

    public UserHelper(String email, String username) {
        this.username=username;
        this.email = email;

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
}
