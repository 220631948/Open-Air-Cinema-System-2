package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Booking;
import za.ac.cput.repository.BookingRepository;

import java.util.List;

@Service
public class BookingService implements IBookingService {

    private final BookingRepository repository;

    @Autowired
    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    @Override
    public Booking create(Booking booking) {
        return repository.save(booking);
    }

    @Override
    public Booking read(Integer id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Booking update(Booking booking) {
        return repository.save(booking);
    }


    @Override
    public List<Booking> getAllBookings() {
        return repository.findAll();
    }
}
