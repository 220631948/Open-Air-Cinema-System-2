package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.factory.CustomerFactory;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:9090/customer";

    private static Customer customer;

    @BeforeAll
    public static void setup() {
        // Initialize Customer object for testing
        customer = CustomerFactory.buildCustomer( "John", "Notutela", "john@example.com", "0724437650", "Password@123");
    }

    @Test
    void a_create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(url, customer, Customer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Customer customerSaved = postResponse.getBody();
        assertNotNull(customerSaved.getCustomerId());
        customer = customerSaved;
        System.out.println("Saved customer: " + customerSaved);
    }

    @Test
    void b_read() {
        String url = BASE_URL + "/read/" + customer.getCustomerId();
        System.out.println("URL: " + url);
        ResponseEntity<Customer> response = restTemplate.getForEntity(url, Customer.class);
        assertNotNull(response.getBody());
        assertEquals(customer.getCustomerId(), response.getBody().getCustomerId());
        System.out.println("Read customer: " + response.getBody());
    }

    @Test
    void c_update() {
        String url = BASE_URL + "/update";
        // Update customer object as needed
        Customer updatedCustomer = new Customer.Builder().copy(customer).setName("Johnny").build();
        ResponseEntity<Customer> postResponse = restTemplate.postForEntity(url, updatedCustomer, Customer.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Customer customerUpdated = postResponse.getBody();
        assertEquals(updatedCustomer.getCustomerId(), customerUpdated.getCustomerId());
        assertEquals(updatedCustomer.getName(), customerUpdated.getName());
        System.out.println("Updated customer: " + customerUpdated);
    }

    @Test
    void d_getAllCustomers() {
        String url = BASE_URL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show ALL Customers: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }

    @Test
    void e_login_ValidCredentials() {
        String url = BASE_URL + "/login?email=" + customer.getEmail() + "&password=" + customer.getPassword();
        ResponseEntity<Customer> response = restTemplate.postForEntity(url, null, Customer.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(customer.getCustomerId(), response.getBody().getCustomerId());
        System.out.println("Login successful with customer: " + response.getBody());
    }
}