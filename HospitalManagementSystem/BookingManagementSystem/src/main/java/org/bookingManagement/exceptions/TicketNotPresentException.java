package org.bookingManagement.exceptions;

public class TicketNotPresentException extends RuntimeException{
    public TicketNotPresentException(String message){
        super(message);
    }
}
