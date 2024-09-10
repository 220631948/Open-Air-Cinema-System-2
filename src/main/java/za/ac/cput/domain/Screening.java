package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int screeningID;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String time;
    private String venue;
    private String type;
    private int capacity;
    private double ticketPrice;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    protected Screening() {
    }

    private Screening(Builder builder) {
        this.screeningID = builder.screeningID;
        this.date = builder.date;
        this.time = builder.time;
        this.venue = builder.venue;
        this.type = builder.type;
        this.capacity = builder.capacity;
        this.ticketPrice = builder.ticketPrice;
        this.movie = builder.movie;
    }

    public int getScreeningID() {
        return screeningID;
    }

    public Date getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getVenue() {
        return venue;
    }

    public String getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public Movie getMovie() {
        return movie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Screening screening = (Screening) o;
        return screeningID == screening.screeningID &&
                capacity == screening.capacity &&
                Double.compare(screening.ticketPrice, ticketPrice) == 0 &&
                Objects.equals(date, screening.date) &&
                Objects.equals(time, screening.time) &&
                Objects.equals(venue, screening.venue) &&
                Objects.equals(type, screening.type) &&
                Objects.equals(movie, screening.movie);
    }

    @Override
    public int hashCode() {
        return Objects.hash(screeningID, date, time, venue, type, capacity, ticketPrice, movie);
    }

    @Override
    public String toString() {
        return "Screening{" +
                "screeningID=" + screeningID +
                ", date=" + date +
                ", time='" + time + '\'' +
                ", venue='" + venue + '\'' +
                ", type='" + type + '\'' +
                ", capacity=" + capacity +
                ", ticketPrice=" + ticketPrice +
                ", movie=" + movie +
                '}';
    }

    public static class Builder {

        private int screeningID;
        private Date date;
        private String time;
        private String venue;
        private String type;
        private int capacity;
        private double ticketPrice;
        private Movie movie;

        public Builder setScreeningID(int screeningID) {
            this.screeningID = screeningID;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setTime(String time) {
            this.time = time;
            return this;
        }

        public Builder setVenue(String venue) {
            this.venue = venue;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Builder setCapacity(int capacity) {
            this.capacity = capacity;
            return this;
        }

        public Builder setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        public Builder setMovie(Movie movie) {
            this.movie = movie;
            return this;
        }

        public Builder copy(Screening screening) {
            this.screeningID = screening.screeningID;
            this.date = screening.date;
            this.time = screening.time;
            this.venue = screening.venue;
            this.type = screening.type;
            this.capacity = screening.capacity;
            this.ticketPrice = screening.ticketPrice;
            this.movie = screening.movie;
            return this;
        }

        public Screening build() {
            return new Screening(this);
        }
    }
}

