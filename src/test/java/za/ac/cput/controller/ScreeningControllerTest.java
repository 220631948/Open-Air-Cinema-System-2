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
import za.ac.cput.domain.Genre;
import za.ac.cput.domain.Movie;
import za.ac.cput.domain.Screening;
import za.ac.cput.factory.GenreFactory;
import za.ac.cput.factory.MovieFactory;
import za.ac.cput.factory.ScreeningFactory;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ScreeningControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:9090/screening";

    private static Screening screening;

    @BeforeAll
    public static void setup() {
        // Initialize Genre objects
        Genre genre = GenreFactory.createGenre( "Action");
        // Initialize Movie objects for testing
        Movie movie = MovieFactory.createMovie( "Inception", "Christopher Nolan", genre, 90, 2010);
        // Initialize Screening objects for testing
        screening = ScreeningFactory.buildScreening( new Date(), "18:00", "Main Venue", "IMAX", 150, 15.00, movie);
    }

    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Screening> postReponse = restTemplate.postForEntity(url, screening, Screening.class);
        assertNotNull(postReponse);
        assertNotNull(postReponse.getBody());
        Screening screeningSaved = postReponse.getBody();
        assertNotNull(screeningSaved.getScreeningID());
        screening = screeningSaved;
        System.out.println("Saved data: "+ screeningSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read" +screening.getScreeningID();
        System.out.println("URL:" + url);
        ResponseEntity<Screening> response = restTemplate.getForEntity(url, Screening.class);
        assertEquals(screening.getScreeningID(), response.getBody().getScreeningID());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update";
        Screening newScreening = new Screening.Builder().copy(screening)
                .setTime("19:00")
                .build();
        ResponseEntity<Screening> postResponse = restTemplate.postForEntity(url, newScreening, Screening.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Screening screeningUpdated = postResponse.getBody();
        assertEquals(newScreening.getScreeningID(), screeningUpdated.getScreeningID());
        System.out.println("Update: " + postResponse.getBody());
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete" + screening.getScreeningID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: Deleted Screening ");
    }

    @Test
    void getAllScreenings() {
        String url = "/getall";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show ALL: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}