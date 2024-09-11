package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "Tickets", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"ticketID", "bookingID", "seatNumber"})
})

@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String ticketID;

    @Column(name = "ticketType", nullable = false)
    private String ticketType;

    @Column(name = "ticketPrice", nullable = false)
    private double ticketPrice;

    @Column(name = "seatNumber", nullable = false, unique = true)
    private String seatNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bookingID", nullable = false)
    private Booking booking;

    protected Ticket() {}

    private Ticket(Builder builder) {
        this.ticketID = builder.ticketID;
        this.ticketType = builder.ticketType;
        this.ticketPrice = builder.ticketPrice;
        this.seatNumber = builder.seatNumber;
        this.booking = builder.booking;
    }

    public String getTicketID() {
        return ticketID;
    }

    public String getTicketType() {
        return ticketType;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public Booking getBooking() {
        return booking;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticketPrice, ticket.ticketPrice) == 0 && Objects.equals(ticketID, ticket.ticketID) && Objects.equals(ticketType, ticket.ticketType) && Objects.equals(seatNumber, ticket.seatNumber) && Objects.equals(booking, ticket.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketID, ticketType, ticketPrice, seatNumber, booking);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketID='" + ticketID + '\'' +
                ", ticketType='" + ticketType + '\'' +
                ", ticketPrice=" + ticketPrice +
                ", seatNumber='" + seatNumber + '\'' +
                ", booking=" + booking +
                '}';
    }

    public static class Builder {
        private String ticketID;
        private String ticketType;
        private double ticketPrice;
        private String seatNumber;
        private Booking booking;

        public Builder setTicketID(String ticketID) {
            this.ticketID = ticketID;
            return this;
        }

        public Builder setTicketType(String ticketType) {
            this.ticketType = ticketType;
            return this;
        }

        public Builder setTicketPrice(double ticketPrice) {
            this.ticketPrice = ticketPrice;
            return this;
        }

        public Builder setSeatNumber(String seatNumber) {
            this.seatNumber = seatNumber;
            return this;
        }

        public Builder setBooking(Booking booking) {
            this.booking = booking;
            return this;
        }

        public Builder copy(Ticket ticket) {
            this.ticketID = ticket.ticketID;
            this.ticketType = ticket.ticketType;
            this.ticketPrice = ticket.ticketPrice;
            this.seatNumber = ticket.seatNumber;
            this.booking = ticket.booking;
            return this;
        }

        public Ticket build() {
            return new Ticket(this);
        }
    }
}