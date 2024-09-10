package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.Payment;
import za.ac.cput.domain.Screening;

import java.util.UUID;

public class BookingFactory {

    public static Booking buildBooking( int quantity, Screening screening, Customer customer) {
        if ( quantity <= 0 || screening == null || customer == null) {
            return null;
        }

        return new Booking.Builder()
                .setQuantity(quantity)
                .setScreening(screening)
                .setCustomer(customer)
                .build();

    }

}

