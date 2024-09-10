package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import za.ac.cput.domain.Movie;
import za.ac.cput.domain.Screening;
import za.ac.cput.service.MovieService;
import za.ac.cput.service.ScreeningService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/screening")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = {"Authorization", "Content-Type", "Accept"})
public class ScreeningController {

    @Autowired
    private ScreeningService screeningService;

    @Autowired
    private MovieService movieService;

    @PostMapping("/create")
    public Screening create(@RequestBody Screening screening) {
        // Fetch the existing movie from the database
        Movie existingMovie = movieService.read(screening.getMovie().getMovieId());
        if (existingMovie == null) {
            throw new RuntimeException("Movie not found");
        }

        // Build the screening with the existing movie and genre
        Screening newScreening = new Screening.Builder()
                .copy(screening)
                .setMovie(existingMovie)
                .build();

        return screeningService.create(newScreening);
    }


    @GetMapping("/read/{screeningId}")
    public Screening read(@PathVariable int screeningId) {
        return screeningService.read(screeningId);
    }

    @PostMapping("/update")
    public Screening update(@RequestBody Screening screening) {
        // Fetch the existing movie from the database
        Movie existingMovie = movieService.read(screening.getMovie().getMovieId());
        if (existingMovie == null) {
            throw new RuntimeException("Movie not found");
        }

        // Build the updated screening with the existing movie and genre
        Screening updatedScreening = new Screening.Builder()
                .copy(screening)
                .setMovie(existingMovie)
                .build();

        return screeningService.update(updatedScreening);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        screeningService.delete(id);
    }

    @GetMapping("/getAll")
    public List<Screening> getAllScreenings() {
        return screeningService.getAllScreenings();
    }
}

