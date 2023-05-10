package com.example.ecomfinal.exception;

public class UserNotFoundException extends RuntimeException{



    public UserNotFoundException(int id) {
        super("Could not found the user with id " + id);
    }

    public UserNotFoundException(String m){super("Could not found the user with username"+ m);}



}
