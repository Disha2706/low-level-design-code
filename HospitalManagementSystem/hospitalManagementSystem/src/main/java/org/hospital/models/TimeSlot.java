package org.hospital.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TimeSlot {
    private String startTime;
    private String endTime;
}
