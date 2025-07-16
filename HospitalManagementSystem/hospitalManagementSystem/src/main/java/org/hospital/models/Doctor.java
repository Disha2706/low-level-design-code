package org.hospital.models;

import lombok.Data;
import org.hospital.enums.Specialization;

import java.util.*;

@Data
public class Doctor implements Person<Patient>{
    private String name;
    private Long id;
    private Specialization specialization;
    private Map<Patient, TimeSlot> allBookings;
    private Map<TimeSlot, Boolean> slotList;
    private static Long count = 0L;

    public Doctor(String name, Specialization specialization){
        this.name = name;
        this.specialization = specialization;
        this.id = count;
        this.allBookings = new HashMap<>();
        this.slotList = new HashMap<>();
        count++;
    }

}
