package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Genre;
import za.ac.cput.domain.Movie;
import za.ac.cput.domain.Screening;
import za.ac.cput.factory.GenreFactory;
import za.ac.cput.factory.MovieFactory;
import za.ac.cput.factory.ScreeningFactory;
import za.ac.cput.repository.MovieRepository;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class ScreeningServiceTest {

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private GenreService genreService;

    private static Screening screening1;
    private static Screening screening2;

    @BeforeEach
    void setUp() {
        // Fetch existing Genre entities by their IDs
        Genre genre1 = genreService.read(1);  // Replace 1 with the ID for "Fantasy"
        Genre genre2 = genreService.read(2);  // Replace 2 with the ID for "Horror"

        // Ensure genres are not null
        assertNotNull(genre1);
        assertNotNull(genre2);

        // Initialize Movie objects
        Movie movie1 = MovieFactory.createMovie("The Lord of the Rings", "Peter Jackson", genre1, 180, 2001);
        Movie movie2 = MovieFactory.createMovie("The Conjuring", "James Wan", genre2, 112, 2013);

        movie1 = movieService.create(movie1);  // Persist the new Fantasy movie
        movie2 = movieService.create(movie2);  // Persist the new Horror movie

        // Step 3: Create Screening objects with the new Movie objects
        screening1 = ScreeningFactory.buildScreening(new Date(), "20:00", "Main Venue", "3D", 250, 20.00, movie1);
        screening2 = ScreeningFactory.buildScreening(new Date(), "22:00", "Main Venue", "2D", 300, 25.00, movie2);

        // Ensure Screening objects are not null
        assertNotNull(screening1);
        assertNotNull(screening2);
    }

    @Test
    void a_create() {
        Screening created1 = screeningService.create(screening1);
        assertNotNull(created1);
        System.out.println("Created screening1: " + created1);

        Screening created2 = screeningService.create(screening2);
        assertNotNull(created2);
        System.out.println("Created screening2: " + created2);
    }

    @Test
    void b_read() {
        int screeningID = 4;  // Replace with a valid movieId from your database
        Screening readScreening = screeningService.read(screeningID);
        assertNotNull(readScreening);  // Ensure the Movie is found
        assertEquals(screeningID, readScreening.getScreeningID());
        System.out.println(readScreening);
    }

    @Test
    void c_update() {
        Screening existingScreening = screeningService.read(5);
        assertNotNull(existingScreening);

        Screening updatedScreening = new Screening.Builder()
                .copy(existingScreening) // Copy the existing screening
                .setTime("19:00")       // Update the time
                .build();

        // Perform the update operation
        Screening updated = screeningService.update(updatedScreening);

        // Check if the update was successful
        assertNotNull(updated);
        assertEquals("19:00", updated.getTime());
        assertEquals(existingScreening.getScreeningID(), updated.getScreeningID());
        System.out.println("Updated screening: " + updated);
    }

    @Test
    void d_getAll() {
            System.out.println(screeningService.getAllScreenings());
        }
}
