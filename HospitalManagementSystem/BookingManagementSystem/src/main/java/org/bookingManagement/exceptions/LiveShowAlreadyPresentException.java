package org.bookingManagement.exceptions;

public class LiveShowAlreadyPresentException extends RuntimeException{

    public LiveShowAlreadyPresentException(String message){
        super(message);
    }
}
