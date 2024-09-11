package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Objects;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String paymentMethod;

    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToOne
    @JoinColumn(name = "bookingID")
    private Booking booking;

    // protected no args constructor
    protected Payment() {
    }

    // all args constructor using the Builder Pattern
    private Payment(Builder builder) {
        this.id = builder.id;
        this.amount = builder.amount;
        this.paymentMethod = builder.paymentMethod;
        this.date = builder.date;
        this.booking = builder.booking;
    }


    // Getters
    public Long getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public Date getDate() {
        return date;
    }

    public Booking getBooking() {
        return booking;
    }

    // Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Payment payment = (Payment) o;
        return Double.compare(amount, payment.amount) == 0 && Objects.equals(id, payment.id) && Objects.equals(paymentMethod, payment.paymentMethod) && Objects.equals(date, payment.date) && Objects.equals(booking, payment.booking);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, paymentMethod, date, booking);
    }

    // To String
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", date=" + date +
                ", booking=" + booking +
                '}';
    }

    // Builder with setters

    public static class Builder{
        private Long id;
        private double amount;
        private String paymentMethod;
        private Date date;
        private Booking booking;

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setDate(Date date) {
            this.date = date;
            return this;
        }

        public Builder setBooking(Booking booking) {
            this.booking = booking;
            return this;
        }

        public Payment build(){
            return new Payment(this);
        }

        public Builder copy(Payment payment){
            this.id = payment.id;
            this.amount = payment.amount;
            this.paymentMethod = payment.paymentMethod;
            this.date = payment.date;
            this.booking = payment.booking;
            return this;
        }
    }

}