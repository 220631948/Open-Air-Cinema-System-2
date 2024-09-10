package za.ac.cput.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Screening;

import java.util.List;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Integer> {
    List<Screening> findByMovieMovieGenreName(String genreName);
}
