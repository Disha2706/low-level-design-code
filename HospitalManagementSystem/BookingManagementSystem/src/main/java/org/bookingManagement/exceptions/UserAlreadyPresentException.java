package org.bookingManagement.exceptions;

public class UserAlreadyPresentException extends RuntimeException{
    public UserAlreadyPresentException(String message){
        super(message);
    }
}
