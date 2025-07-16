package org.bookingManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Slot {
    private int startHour;
    private int capacity;
}
