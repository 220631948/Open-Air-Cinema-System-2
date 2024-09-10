package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Genre;
import za.ac.cput.domain.Screening;
import za.ac.cput.repository.GenreRepository;
import za.ac.cput.repository.ScreeningRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ScreeningService implements IScreeningService {

    private final ScreeningRepository screeningRepository;
    private final GenreRepository genreRepository;

    @Autowired
    public ScreeningService(ScreeningRepository screeningRepository, GenreRepository genreRepository) {
        this.screeningRepository = screeningRepository;
        this.genreRepository = genreRepository;
    }

    @Override
    public Screening create(Screening screening) {
        return screeningRepository.save(screening);
    }

    @Override
    public Screening read(Integer id) {
        return screeningRepository.findById(id).orElse(null);
    }

    @Override
    public Screening update(Screening screening) {
        return screeningRepository.save(screening);
    }

    @Override
    public void delete(int screeningID) {
        screeningRepository.deleteById(screeningID);
    }

    @Override
    public List<Screening> getAllScreenings() {
        return screeningRepository.findAll();
    }

    public List<Screening> getScreeningsByGenreName(String genreName) {
        return screeningRepository.findByMovieMovieGenreName(genreName);
    }
}

