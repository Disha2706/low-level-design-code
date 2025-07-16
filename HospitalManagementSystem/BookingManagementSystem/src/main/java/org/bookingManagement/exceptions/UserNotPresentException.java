package org.bookingManagement.exceptions;

public class UserNotPresentException extends RuntimeException{
    public UserNotPresentException(String message){
        super(message);
    }
}
