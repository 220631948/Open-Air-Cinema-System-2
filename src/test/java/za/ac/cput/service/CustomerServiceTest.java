package za.ac.cput.service;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;

    private static Customer customer1;
    private static Customer customer2;

    @Test
    void a_setUp() {
        customer1 = CustomerFactory.buildCustomer( "John", "Notutela", "john@example.com", "0724437650", "Password1@");
        customer2 = CustomerFactory.buildCustomer( "Jane", "Smith", "jane@example.com", "0987654321", "Password2@");

        customerService.create(customer1);
        customerService.create(customer2);

        assertNotNull(customer1);
        assertNotNull(customer2);

        System.out.println("Customer 1: " + customer1);
        System.out.println("Customer 2: " + customer2);
    }

    @Test
    void b_create() {
        // Creating another customer for testing the create method
        Customer customer = CustomerFactory.buildCustomer( "Michael", "Johnson", "michael@example.com", "1234567890", "Password3@");
        Customer createdCustomer = customerService.create(customer);

        assertNotNull(createdCustomer);
        System.out.println("Created Customer: " + createdCustomer);
    }

    @Test
    void c_read() {
        Customer readCustomer = customerService.read(customer1.getCustomerId());
        assertNotNull(readCustomer);
        assertEquals(customer1.getCustomerId(), readCustomer.getCustomerId());

        System.out.println("Read Customer: " + readCustomer);
    }

    @Test
    void d_testUpdate() {
        // Fetch the existing customer
        Customer existingCustomer = customerService.read(customer1.getCustomerId());
        assertNotNull(existingCustomer);

        // Update the customer's name
        Customer updatedCustomer = new Customer.Builder()
                .copy(existingCustomer)
                .setName("Johnny")
                .build();

        updatedCustomer = customerService.update(updatedCustomer);

        assertNotNull(updatedCustomer);
        assertEquals(existingCustomer.getCustomerId(), updatedCustomer.getCustomerId());
        assertEquals("Johnny", updatedCustomer.getName());

        System.out.println("Updated Customer: " + updatedCustomer);
    }

    @Test
    void e_getAll() {
        System.out.println("All Customers: " + customerService.getAll());
    }
}