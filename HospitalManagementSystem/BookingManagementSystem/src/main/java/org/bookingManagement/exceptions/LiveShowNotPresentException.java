package org.bookingManagement.exceptions;

public class LiveShowNotPresentException extends RuntimeException{
    public LiveShowNotPresentException(String message){
        super(message);
    }
}
