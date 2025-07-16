package org.hospital.models;

import lombok.Data;

import java.util.List;

@Data
public class AvailableDoctor {
    Doctor doctor;
    List<TimeSlot> timeSlotList;
}
