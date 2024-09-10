package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookingID;
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "screeningID")
    private Screening screening;

    @ManyToOne
    @JoinColumn(name = "customerID")
    private Customer customer;


    protected Booking() {
    }

    private Booking(Builder builder) {
        this.bookingID = builder.bookingID;
        this.quantity = builder.quantity;
        this.screening = builder.screening;
        this.customer = builder.customer;
    }

    public int getBookingID() {
        return bookingID;
    }

    public int getQuantity() {
        return quantity;
    }

    public Screening getScreening() {
        return screening;
    }

    public Customer getCustomer() {
        return customer;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingID == booking.bookingID &&
                quantity == booking.quantity &&
                Objects.equals(screening, booking.screening) &&
                Objects.equals(customer, booking.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingID, quantity, screening, customer);
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", quantity=" + quantity +
                ", screening=" + screening +
                ", customer=" + customer +
                '}';
    }

    public static class Builder {

        private int bookingID;
        private int quantity;
        private Screening screening;
        private Customer customer;

        public Builder setBookingID(int bookingID) {
            this.bookingID = bookingID;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setScreening(Screening screening) {
            this.screening = screening;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder copy(Booking booking) {
            this.bookingID = booking.bookingID;
            this.quantity = booking.quantity;
            this.screening = booking.screening;
            this.customer = booking.customer;
            return this;
        }

        public Booking build() {
            return new Booking(this);
        }
    }
}
