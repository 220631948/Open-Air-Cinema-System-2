package za.ac.cput.service.ticket;

import org.springframework.stereotype.Service;
import za.ac.cput.domain.Ticket;
import za.ac.cput.repository.TicketRepository;

import java.util.List;

@Service
public interface ITicketService {
    // crud methods
    Ticket create(Ticket ticket);
    Ticket read(String ticketID);
    Ticket update(Ticket ticket);
    void delete(String ticketID);
    List<Ticket> getAll();


//    // additional methods
//    List<Ticket> findByBooking_BookingID(int bookingID);
//    List<Ticket> findBySeatNumber(String seatNumber);
//    List<Ticket> findByBooking_BookingIDAAndSeatNumber(int bookingID, String seatNumber);
//
//    // find all tickets for a specific movie, find all tickets for a specific customer
//    List<Ticket> findtcketByCustomer(String customer);

}