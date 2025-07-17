package org.bookingManagement.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private String name;
    private List<Ticket> bookedTickets;
    private Integer userId;
    private static Integer count=1;

    public User(String name){
        this.name = name;
        bookedTickets = new ArrayList<>();
        userId = count++;
    }

    public boolean hasConflict(int hour) {
        return bookedTickets.stream().anyMatch(t -> t.getSlotHour() == hour);
    }

    public void addTicket(Ticket ticket) {
        bookedTickets.add(ticket);
    }

    public void cancelTicket(Ticket ticket) {
        bookedTickets.remove(ticket);
    }
}
