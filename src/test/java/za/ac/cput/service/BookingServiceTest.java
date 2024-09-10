package za.ac.cput.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class BookingServiceTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private ScreeningService screeningService;

    private static Booking booking1;
    private static Booking booking2;

    @BeforeEach
    void a_setup() {

        Genre genre1 = GenreFactory.createGenre( "Action");
        Genre genre2 = GenreFactory.createGenre( "Drama");
        // Initialize Customer objects for testing
        Customer customer1 = CustomerFactory.buildCustomer( "Johnny", "Notutela", "john@example.com", "0724437650", "Notutela@1");

        Customer customer2 = CustomerFactory.buildCustomer( "Jane", "Smith", "jane@example.com", "0987654321", "Smith@2024");

        Movie movie1 = MovieFactory.createMovie( "The Matrix", "The Wachowski", genre1, 120, 1999);
        Movie movie2 = MovieFactory.createMovie( "Inception", "Christopher Nolan", genre2, 90, 2010);

        assertNotNull(customer1);
        assertNotNull(customer2);
        assertNotNull(movie1);
        assertNotNull(movie2);

        // Initialize Screening objects for testing
        Screening screening1 = ScreeningFactory.buildScreening( new Date(), "18:00", "Venue 1", "Type 1", 100, 10.0, movie1);
        Screening screening2 = ScreeningFactory.buildScreening( new Date(), "20:00", "Venue 2", "Type 2", 120, 12.0, movie2);

        // Save Screening entities to the database
        screeningService.create(screening1);
        screeningService.create(screening2);

        assertNotNull(screening1);
        assertNotNull(screening2);

        // Initialize Booking objects for testing
        booking1 = BookingFactory.buildBooking( 3, screening1, customer1);
        assertNotNull(booking1);
        System.out.println(booking1);

        booking2 = BookingFactory.buildBooking( 2, screening2, customer2);
        assertNotNull(booking2);
        System.out.println(booking2);
    }


    @Test
    @Order(1)
    void b_create() {
        // Assuming booking1 and booking2 are initialized in a_setup()

        // Save booking1 and booking2 to the database
        Booking created1 = bookingService.create(booking1);
        assertNotNull(created1);
        System.out.println(created1);

        Booking created2 = bookingService.create(booking2);
        assertNotNull(created2);
        System.out.println(created2);
    }

    @Test
    @Order(2)
    void c_read() {
        // Assuming booking1 and booking2 are initialized in a_setup()

        Booking created1 = bookingService.create(booking1);
        assertNotNull(created1);

        Booking readBooking1 = bookingService.read(created1.getBookingID());
        assertNotNull(readBooking1);
        assertEquals(created1.getBookingID(), readBooking1.getBookingID());
        // Add more assertions for other attributes as needed
    }

    @Test
    @Order(3)
    void d_update() {
        // Assuming booking1 and booking2 are initialized in a_setup()

        Booking created1 = bookingService.create(booking1);
        assertNotNull(created1);

        // Update booking1
        Booking updatedBooking1 = new Booking.Builder()
                .copy(created1)  // Copy the existing booking details
                .setQuantity(10) // Update the quantity
                .build();

        Booking updated1 = bookingService.update(booking1);
        assertNotNull(updated1);
        assertEquals(10, updated1.getQuantity()); // Example assertion
        assertEquals(created1.getBookingID(), updated1.getBookingID());
        // Add more assertions for other attributes as needed
    }

    @Test
    @Order(4)
    void e_getAllBookings() {
            System.out.println(bookingService.getAllBookings());
        }
}