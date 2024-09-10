package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookingControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:9090/booking";

    private static Booking booking;

    @BeforeAll
    public static void setup() {
        Genre genre = GenreFactory.createGenre( "Action");
        Movie movie = MovieFactory.createMovie( "Inception", "Christopher Nolan", genre, 90, 2010);
        Customer customer = CustomerFactory.buildCustomer( "Johnny", "Notutela", "john@example.com", "0724437650", "Notutela@1");
        Screening screening = ScreeningFactory.buildScreening( new Date(), "18:00", "Main Venue", "IMAX", 150, 15.00, movie);
        booking = BookingFactory.buildBooking( 2, screening, customer); // Set ID to 0 or omit
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Booking> postResponse = restTemplate.postForEntity(url, booking, Booking.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Booking bookingSaved = postResponse.getBody();
        assertNotNull(bookingSaved.getBookingID()); // Ensure the ID is not null after saving
        booking = bookingSaved; // Update the static booking with the saved instance for further tests
        System.out.println("Saved data: " + bookingSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + booking.getBookingID();
        System.out.println("URL: " + url);
        ResponseEntity<Booking> response = restTemplate.getForEntity(url, Booking.class);
        assertNotNull(response.getBody());
        assertEquals(booking.getBookingID(), response.getBody().getBookingID()); // Assert the IDs match
        System.out.println("Read: " + response.getBody());
    }


    @Test
    void update() {
        String url = BASE_URL + "/update";
        Booking updatedBooking = new Booking.Builder().copy(booking)
                .setQuantity(3) // Update the quantity field
                .build();
        ResponseEntity<Booking> postResponse = restTemplate.postForEntity(url, updatedBooking, Booking.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Booking bookingUpdated = postResponse.getBody();
        assertEquals(updatedBooking.getBookingID(), bookingUpdated.getBookingID());
        System.out.println("Update: " + postResponse.getBody());
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + booking.getBookingID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: Deleted Booking");
    }

    @Test
    void getAllBookings() {
        String url = BASE_URL + "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show ALL: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}