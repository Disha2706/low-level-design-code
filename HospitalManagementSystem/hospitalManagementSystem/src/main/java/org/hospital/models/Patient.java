package org.hospital.models;

import lombok.Data;

import java.util.*;

@Data
public class Patient implements Person<Doctor>{
    private String name;
    private Long id;
    private Map<Doctor, List<TimeSlot>> bookedSlots;
    private Set<TimeSlot> bookedSlotsList;
    private static Long count = 0L;

    public Patient(String name){
        this.name = name;
        this.id = count;
        bookedSlots = new HashMap<>();
        bookedSlotsList = new HashSet<>();
        count++;
    }

}
