package za.ac.cput.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password; // New field

    protected Customer() {
        // Default constructor for JPA
    }

    private Customer(Builder builder) {
        this.customerId = builder.customerId;
        this.name = builder.name;
        this.surname = builder.surname;
        this.email = builder.email;
        this.phone = builder.phone;
        this.password = builder.password; // Initialize new field
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() { // Getter for new field
        return password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerId=" + customerId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' + // Include new field in toString
                '}';
    }

    public static class Builder {
        private int customerId;
        private String name;
        private String surname;
        private String email;
        private String phone;
        private String password; // New field

        public Builder setCustomerId(int customerId) {
            this.customerId = customerId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setPassword(String password) { // New setter
            this.password = password;
            return this;
        }

        public Builder copy(Customer customer) {
            this.customerId = customer.customerId;
            this.name = customer.name;
            this.surname = customer.surname;
            this.email = customer.email;
            this.phone = customer.phone;
            this.password = customer.password; // Copy new field
            return this;
        }

        public Customer build() {
            return new Customer(this);
        }
    }
}