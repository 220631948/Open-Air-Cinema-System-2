package za.ac.cput.factory;

import org.junit.jupiter.api.Test;
import za.ac.cput.domain.Customer;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerFactoryTest {

    @Test
    void buildCustomer() {
        Customer customer = CustomerFactory.buildCustomer(
                "John",
                "Notutela",
                "john@example.com",
                "0724437650",
                "Luba@20246"
        );

        assertNotNull(customer);
        assertEquals("John", customer.getName());
        assertEquals("Notutela", customer.getSurname());
        assertEquals("john@example.com", customer.getEmail());
        assertEquals("0724437650", customer.getPhone());

        System.out.println(customer.toString());
    }
}
