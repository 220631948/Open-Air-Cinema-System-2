package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

public class BookingFactoryTest {

    @Test
    void buildBooking() {

        // Create mock Genre object using Builder pattern
        Genre genre = new Genre.Builder()
                .setGenreID(1)
                .setName("Action")
                .build();


        // Create mock Movie object using Builder pattern
        Movie movie = new Movie.Builder()
                .setMovieId(1)
                .setMovieTitle("Example Movie")
                .setMovieDirector("Director Name")
                .setMovieGenre(genre)
                .setMovieReleaseDate(2022)
                .build();

        // Create Screening object using Builder pattern
        Screening screening = new Screening.Builder()
                .setScreeningID(1)
                .setDate(new Date())
                .setTime("18:00")
                .setVenue("Main Venue")
                .setType("IMAX")
                .setCapacity(150)
                .setTicketPrice(15.00)
                .setMovie(movie)
                .build();

        // Create Customer object using Builder pattern
        Customer customer = new Customer.Builder()
                .setCustomerId(1)
                .setName("John")
                .setSurname("Doe")
                .setEmail("john@example.com")
                .setPhone("1234567890")
                .build();

        // Create Payment object using Builder pattern
        Payment payment = new Payment.Builder()
                .setPaymentID(1)
                .setAmount(15.00)
                .setDate(new Date())
                .setPaymentMethod("Credit Card")
                .build();

        // Create Booking object using BookingFactory
        Booking booking = BookingFactory.buildBooking(
                2,
                screening,
                customer
        );

        assertNotNull(booking);
        System.out.println(booking.toString());
    }

}
