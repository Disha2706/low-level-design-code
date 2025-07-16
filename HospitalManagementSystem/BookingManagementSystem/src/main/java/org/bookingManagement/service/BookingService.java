package org.bookingManagement.service;

import org.bookingManagement.exceptions.SlotException;
import org.bookingManagement.exceptions.TicketNotPresentException;
import org.bookingManagement.model.*;
import org.bookingManagement.repository.LiveShowRepository;
import org.bookingManagement.repository.UserRepository;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class BookingService {
    private LiveShowRepository liveShowRepository;
    private UserRepository userRepository;
    private Map<Integer, Ticket> bookings = new HashMap<>();
    private Queue<WaitListEntry> waitlist = new LinkedList<>();

    public boolean bookTicket(User user, int persons, LiveShow show, int startTime) {
        Slot slot = show.getSlot(startTime);
        int capacity = slot.getCapacity();
        if (persons > capacity) {
            System.out.println("Booking persons exceeds capacity!");
            return false;
        }
        if(user.hasConflict(startTime))
            throw new SlotException("user already booked for the slot");
        if (capacity >= persons) {
            capacity -= persons;
            slot.setCapacity(capacity);
            show.updateSlotCapacity(startTime, capacity);
            Ticket ticket = new Ticket(show, user, persons, startTime);
            bookings.put(ticket.getId(), ticket);
            user.addTicket(ticket);
            return true;
        } else {
            waitlist.add(new WaitListEntry(user, persons, startTime));
            return false;
        }
    }

    public void cancelTicket(Integer ticketId) throws TicketNotPresentException {
        if(!bookings.containsKey(ticketId))
            throw new TicketNotPresentException("Ticket must be booked before it is cancelled!");
        Ticket toCancel = bookings.get(ticketId);
        bookings.remove(toCancel);
        int capacity = toCancel.getPersons();

        while (!waitlist.isEmpty()) {
            WaitListEntry entry = waitlist.peek();
            if (entry.getPersons() <= capacity && entry.getStartTime() == toCancel.getSlotHour()) {
                waitlist.poll();
                bookTicket(entry.getUser(), entry.getPersons(), toCancel.getLiveShow(), toCancel.getSlotHour());
            } else break;
        }
    }

}
