package org.bookingManagement.exceptions;

public class NoGenrePresentException extends RuntimeException{
    public NoGenrePresentException(String message){
        super(message);
    }
}
