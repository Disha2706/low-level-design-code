package org.bookingManagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class Ticket {
    private Integer id;
    private LiveShow liveShow;
    private User user;
    private int persons;
    private int slotHour;

    private static Integer count = 1;

    public Ticket(LiveShow liveShow, User user, int persons, int slotHour) {
        this.id = count++;
        this.liveShow = liveShow;
        this.user = user;
        this.persons = persons;
        this.slotHour = slotHour;
    }
}
