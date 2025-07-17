package org.bookingManagement;

import org.bookingManagement.enums.Genre;
import org.bookingManagement.exceptions.LiveShowAlreadyPresentException;
import org.bookingManagement.exceptions.SlotException;
import org.bookingManagement.exceptions.TicketNotPresentException;
import org.bookingManagement.model.LiveShow;
import org.bookingManagement.model.User;
import org.bookingManagement.repository.LiveShowRepository;
import org.bookingManagement.repository.UserRepository;
import org.bookingManagement.service.BookingService;


public class Main {
    public static void main(String[] args) {
        BookingService manager = BookingService.getInstance();
        LiveShowRepository liveShowRepository = LiveShowRepository.INSTANCE;
        UserRepository userRepository = UserRepository.INSTANCE;

        // Register Show
        LiveShow liveShow = new LiveShow("TMKOC", Genre.COMEDY);
        liveShowRepository.registerLiveShow(liveShow);
        System.out.println("o: TMKOC show is registered !!");

        // Invalid Slot
        System.out.println("i: onboardShowSlots: TMKUL y:UU-11:00");
        if(isOneHour("9", "10")) System.out.println("Invalid input");;
        System.out.println("o: Sorry, show timings are of 1 hour only");

        // Valid Slot onboarding
        liveShowRepository.addSlotToShow("TMKOC", 9, 3);
        liveShowRepository.addSlotToShow("TMKOC", 12, 2);
        liveShowRepository.addSlotToShow("TMKOC", 15, 5);
        System.out.println("o: Done!");

        // Register 2nd show
        liveShow = new LiveShow("The Sonu Nigam Live Event", Genre.SINGING);
        liveShowRepository.registerLiveShow(liveShow);
//        liveShowRepository.registerLiveShow(liveShow);

        // Onboard slots for Sonu show
        liveShowRepository.addSlotToShow("The Sonu Nigam Live Event", 10, 3);
        liveShowRepository.addSlotToShow("The Sonu Nigam Live Event", 13, 2);
        liveShowRepository.addSlotToShow("The Sonu Nigam Live Event", 17, 1);
        System.out.println("o: Done!");

        // Show Avail By Genre: Comedy
        liveShowRepository.getLiveShowsByStartTime(Genre.COMEDY).forEach(show ->
                show.getSlotMap().values().forEach(slot ->
                        System.out.println("o: " + show.getName() + ": (" + formatHour(slot.getStartHour()) + "-" + formatHour(slot.getStartHour() + 1) + ") " + slot.getCapacity())
                )
        );

        // Cancel Booking
        try{
            manager.cancelTicket(1234);
        } catch (TicketNotPresentException e){

        }

        // Book Ticket
        User user = new User("UserB");
        userRepository.registerUser(user);
        Integer booked = manager.bookTicket(user, 2,liveShowRepository.getLiveShow("TMKOC"),  12);
        System.out.println("Booked: " + booked);

        // Show Avail By Genre: Comedy
        liveShowRepository.getLiveShowsByStartTime(Genre.COMEDY).forEach(show ->
                show.getSlotMap().values().forEach(slot ->
                        System.out.println("o: " + show.getName() + ": (" + formatHour(slot.getStartHour()) + "-" + formatHour(slot.getStartHour() + 1) + ") " + slot.getCapacity())
                )
        );
        try{
            Integer booked2 = manager.bookTicket(user, 1,liveShowRepository.getLiveShow("TMKOC"),  12);
        } catch (SlotException e){}

        User user2 = new User("UserA");
        User user3 = new User("UserB");
        Integer booked2 = manager.bookTicket(user2, 3,liveShowRepository.getLiveShow("TMKOC"),  12);
        manager.bookTicket(user3, 1, liveShowRepository.getLiveShow("TMKOC"),  12);
        manager.cancelTicket(booked);

        liveShowRepository.getLiveShowsByStartTime(Genre.COMEDY).forEach(show ->
                show.getSlotMap().values().forEach(slot ->
                        System.out.println("o: " + show.getName() + ": (" + formatHour(slot.getStartHour()) + "-" + formatHour(slot.getStartHour() + 1) + ") " + slot.getCapacity())
                )
        );

        // Register Arijit Show
        liveShow = new LiveShow("The Arijit Singh Live Event", Genre.SINGING);
        try{
            liveShowRepository.registerLiveShow(liveShow);
            liveShowRepository.registerLiveShow(liveShow);
        } catch (LiveShowAlreadyPresentException e){}

    }
    private static boolean isOneHour(String start, String end) {
        return parseHour(end) - parseHour(start) == 1;
    }

    private static int parseHour(String time) {
        return Integer.parseInt(time.split(":")[0]);
    }
    private static String formatHour(int hour) {
        return String.format("%02d:00", hour);
    }
}