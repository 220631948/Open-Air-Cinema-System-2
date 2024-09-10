package za.ac.cput.factory;

import za.ac.cput.domain.Screening;
import za.ac.cput.domain.Movie;
import za.ac.cput.util.Helper;

import java.util.Date;
import java.util.UUID;

public class ScreeningFactory {

    public static Screening buildScreening( Date date, String time, String venue, String type, int capacity, double ticketPrice, Movie movie) {
        // Validate inputs
        if ( date == null || Helper.isNullOrEmpty(time) || Helper.isNullOrEmpty(venue) ||
                Helper.isNullOrEmpty(type) || capacity <= 0 || ticketPrice < 0 || movie == null)
            return null;

        // Create and return the Screening object
        return new Screening.Builder()
                .setDate(date)
                .setTime(time)
                .setVenue(venue)
                .setType(type)
                .setCapacity(capacity)
                .setTicketPrice(ticketPrice)
                .setMovie(movie)
                .build();
    }

}

