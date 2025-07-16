package org.hospital.exceptions;

public class SlotUnavailableException extends  RuntimeException{
    public SlotUnavailableException(){
        super("Patient is Already Occupied or Doctor Unavailable");
    }
}
