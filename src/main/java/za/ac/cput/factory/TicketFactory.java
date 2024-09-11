package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Ticket;
import za.ac.cput.utils.HelperUtils;

// Add methods for creating Ticket objects
// Use the Builder pattern to create Ticket objects


public class TicketFactory {
    public static Ticket createTicket(String ticketID, String ticketType, double ticketPrice, String seatNumber, Booking booking) {
        // Validate the input using the HelperUtils class
        // Return a new Ticket object
        if (HelperUtils.isNullOrEmpty(ticketID)
                || HelperUtils.isNullOrEmpty(ticketType)
                || ticketPrice < 0
                || HelperUtils.isNullOrEmpty(seatNumber)
                || booking == null)
            return null;

        Booking booking1 = new Booking.Builder().build();

        // Return a new Ticket object

        return new Ticket.Builder()
                .setTicketID(ticketID)
                .setTicketType(ticketType)
                .setTicketPrice(ticketPrice)
                .setSeatNumber(seatNumber)
                .setBooking(booking1)
                .build();
    }

    public static Ticket createTicket(String ticketID, String ticketType, double ticketPrice, String seatNumber) {
        // Validate the input using the HelperUtils class
        if (HelperUtils.isNullOrEmpty(ticketID)
                || HelperUtils.isNullOrEmpty(ticketType)
                || ticketPrice < 0
                || HelperUtils.isNullOrEmpty(seatNumber))
            return null;

        // Return a new Ticket object
        return new Ticket.Builder()
                .setTicketID(ticketID)
                .setTicketType(ticketType)
                .setTicketPrice(ticketPrice)
                .setSeatNumber(seatNumber)
                .build();
    }
}