package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Genre;
import za.ac.cput.domain.Movie;
import za.ac.cput.domain.Screening;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;



public class ScreeningFactoryTest {

    @Test
    void buildScreening() {

        Genre genre1 = GenreFactory.createGenre( "Action");
        // Create mock Movie object using builder pattern
        Movie movie = new Movie.Builder()
                .setMovieId(1)
                .setMovieTitle("Example Movie")
                .setMovieDirector("Director Name")
                .setMovieGenre(genre1)
                .setMovieReleaseDate(2022)
                .build();

        // Test building screening with valid parameters
        Screening screening = ScreeningFactory.buildScreening(
                new Date(),
                "18:00",
                "Main Venue",
                "IMAX",
                150,
                15.00,
                movie
        );

        assertNotNull(screening);
        System.out.println(screening.toString());
    }

    @Test
    void buildScreeningWithInvalidCapacity() {

        Genre genre1 = GenreFactory.createGenre( "Action");
        // Create mock Movie object using builder pattern
        Movie movie = new Movie.Builder()
                .setMovieTitle("Example Movie")
                .setMovieDirector("Director Name")
                .setMovieGenre(genre1)
                .setMovieReleaseDate(2022)
                .build();

        // Test building screening with invalid capacity
        Screening screening = ScreeningFactory.buildScreening(
                new Date(),
                "18:00",
                "Main Venue",
                "IMAX",
                -10, // Invalid: capacity less than or equal to 0
                15.00,
                movie
        );

        assertNull(screening); // Expect null since capacity is invalid
        System.out.println("Screening with invalid capacity: " + screening);
    }

    @Test
    void buildScreeningWithNullMovie() {
        // Test building screening with null movie
        Screening screening = ScreeningFactory.buildScreening(
                new Date(),
                "18:00",
                "Main Venue",
                "IMAX",
                150,
                15.00,
                null // Invalid: movie is null
        );

        assertNull(screening); // Expect null since movie is null
        System.out.println("Screening with null movie: " + screening);
    }
}
