package za.ac.cput.factory;

import za.ac.cput.domain.Customer;
import za.ac.cput.util.Helper;

public class CustomerFactory {
    public static Customer buildCustomer(String name, String surname, String email, String phone, String password) {
        // Validate fields
        if (Helper.isNullOrEmpty(name) || Helper.isNullOrEmpty(surname) ||
                Helper.isNullOrEmpty(email) || Helper.isNullOrEmpty(phone) || !Helper.isValidEmail(email) ||
                !Helper.isNumeric(phone) || !Helper.isValidPassword(password)) {
            return null;
        }

        return new Customer.Builder()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPhone(phone)
                .setPassword(password)
                .build();
    }
}
