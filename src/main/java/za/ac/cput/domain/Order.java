package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    @ManyToOne
    @JoinColumn(name = "customerID", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "venueID", nullable = false)
    private Venue venue;

    private double totalAmount;

    private LocalDate orderDate; // Changed to LocalDate for better handling

    @ManyToOne // Assuming foodItem is a reference to an entity
    @JoinColumn(name = "foodItemID") // Adjusted the column name if it's meant to be a foreign key
    private FoodItem foodItem;

    @ManyToOne // Assuming promo is a reference to an entity
    @JoinColumn(name = "promoID") // Adjusted the column name if it's meant to be a foreign key
    private Promo promo;

    protected Order() {
        // Default constructor for JPA
    }

    private Order(Builder builder) {
        this.orderID = builder.orderID;
        this.customer = builder.customer;
        this.venue = builder.venue;
        this.totalAmount = builder.totalAmount;
        this.orderDate = builder.orderDate;
        this.foodItem = builder.foodItem;
        this.promo = builder.promo;
    }

    public int getOrderID() {
        return orderID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Venue getVenue() {
        return venue;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public Promo getPromo() {
        return promo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", customer=" + customer +
                ", venue=" + venue +
                ", totalAmount=" + totalAmount +
                ", orderDate=" + orderDate +
                ", foodItem=" + foodItem +
                ", promo=" + promo +
                '}';
    }

    public static class Builder {
        private int orderID;
        private Customer customer;
        private Venue venue;
        private double totalAmount;
        private LocalDate orderDate;
        private FoodItem foodItem;
        private Promo promo;

        public Builder setOrderID(int orderID) {
            this.orderID = orderID;
            return this;
        }

        public Builder setCustomer(Customer customer) {
            this.customer = customer;
            return this;
        }

        public Builder setVenue(Venue venue) {
            this.venue = venue;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setOrderDate(LocalDate orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setFoodItem(FoodItem foodItem) {
            this.foodItem = foodItem;
            return this;
        }

        public Builder setPromo(Promo promo) {
            this.promo = promo;
            return this;
        }

        public Builder copy(Order order) {
            this.orderID = order.orderID;
            this.customer = order.customer;
            this.venue = order.venue;
            this.totalAmount = order.totalAmount;
            this.orderDate = order.orderDate;
            this.foodItem = order.foodItem;
            this.promo = order.promo;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}