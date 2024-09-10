package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Screening;
import za.ac.cput.service.BookingService;
import za.ac.cput.service.CustomerService;
import za.ac.cput.service.ScreeningService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = {"Authorization", "Content-Type", "Accept"})
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ScreeningService screeningService;

    @PostMapping("/create")
    public Booking create(@RequestBody Booking booking) {
        // Fetch the existing Customer from the database
        Customer existingCustomer = customerService.read(booking.getCustomer().getCustomerId());
        if (existingCustomer == null) {
            throw new RuntimeException("Customer not found");
        }

        // Fetch the existing Screening from the database
        Screening existingScreening = screeningService.read(booking.getScreening().getScreeningID());
        if (existingScreening == null) {
            throw new RuntimeException("Screening not found");
        }

        // Build the booking with the existing customer and screening
        Booking newBooking = new Booking.Builder()
                .copy(booking)
                .setCustomer(existingCustomer)
                .setScreening(existingScreening)
                .build();

        return bookingService.create(newBooking);
    }


    @GetMapping("/booking/{bookingID}")
    public Booking read(@PathVariable int bookingID) {
        return bookingService.read(bookingID);
    }

    @PostMapping("/update")
    public Booking update(@RequestBody Booking booking) {
        // Fetch the existing Customer from the database
        Customer existingCustomer = customerService.read(booking.getCustomer().getCustomerId());
        if (existingCustomer == null) {
            throw new RuntimeException("Customer not found");
        }

        // Fetch the existing Screening from the database
        Screening existingScreening = screeningService.read(booking.getScreening().getScreeningID());
        if (existingScreening == null) {
            throw new RuntimeException("Screening not found");
        }

        // Build the updated booking with the existing customer and screening
        Booking updatedBooking = new Booking.Builder()
                .copy(booking)
                .setCustomer(existingCustomer)
                .setScreening(existingScreening)
                .build();

        return bookingService.update(updatedBooking);
    }

    @GetMapping("/getAll")
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }
}
