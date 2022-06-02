package com.example.demo.exeption;


public class UserNotFoundException extends Exception {
    private long user_id;

    public UserNotFoundException(long user_id) {
        super(String.format("User is not found with id : '%s'", user_id));
        this.user_id = user_id;
    }

    public long getUser_id() {
        return user_id;
    }
}
