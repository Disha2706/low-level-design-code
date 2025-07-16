package org.bookingManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WaitListEntry {
    private User user;
    private int persons;
    private int startTime;
}
