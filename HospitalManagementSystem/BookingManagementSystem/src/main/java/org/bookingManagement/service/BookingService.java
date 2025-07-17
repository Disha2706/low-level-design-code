package org.bookingManagement.service;

import org.bookingManagement.exceptions.SlotException;
import org.bookingManagement.exceptions.TicketNotPresentException;
import org.bookingManagement.model.*;

import java.util.*;

public class BookingService {
    private static volatile BookingService instance;
    private Map<Integer, Ticket> bookings = new HashMap<>();
    private Queue<WaitListEntry> waitlist = new LinkedList<>();

    private BookingService(){}

    public static BookingService getInstance(){
        if(instance == null){
            synchronized ((BookingService.class)){
                instance = new BookingService();
            }
        }
        return instance;
    }

    public Integer bookTicket(User user, int persons, LiveShow show, int startTime) {
        Slot slot = show.getSlot(startTime);
        int capacity = slot.getCapacity();
        if(user.hasConflict(startTime))
            throw new SlotException("user already booked for the slot");
        if (capacity >= persons) {
            capacity -= persons;
            slot.setCapacity(capacity);
            show.updateSlotCapacity(startTime, capacity);
            Ticket ticket = new Ticket(show, user, persons, startTime);
            bookings.put(ticket.getId(), ticket);
            user.addTicket(ticket);
            return ticket.getId();
        } else {
            System.out.println("Booking persons exceeds capacity! User waitlisted");
            waitlist.add(new WaitListEntry(user, persons, startTime));
            return -1;
        }
    }

    public void cancelTicket(Integer ticketId) throws TicketNotPresentException {
        if(!bookings.containsKey(ticketId))
            throw new TicketNotPresentException("Ticket must be booked before it is cancelled!");
        Ticket toCancel = bookings.get(ticketId);
        bookings.remove(toCancel);
        LiveShow show = toCancel.getLiveShow();
        int capacity = toCancel.getPersons();
        show.updateSlotCapacity(toCancel.getSlotHour(), show.getSlotMap().get(toCancel.getSlotHour()).getCapacity() + capacity);

        Iterator<WaitListEntry> iterator = waitlist.iterator();
        while (iterator.hasNext()) {
            WaitListEntry entry = iterator.next();
            if (entry.getPersons() <= capacity && entry.getStartTime() == toCancel.getSlotHour()) {
                iterator.remove();
                bookTicket(entry.getUser(), entry.getPersons(), toCancel.getLiveShow(), toCancel.getSlotHour());
                break;
            }
        }
    }

}
