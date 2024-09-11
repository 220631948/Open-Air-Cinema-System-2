package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Ticket;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    //find tickets by booking ID
    List<Ticket> findByBooking_BookingID(int bookingID);

    //find all tickets by seat number
    List<Ticket> findBySeatNumber(String seatNumber);

    // find tickets by booking ID and seat number
    List<Ticket> findByBooking_BookingIDAAndSeatNumber(int bookingID, String seatNumber);


}