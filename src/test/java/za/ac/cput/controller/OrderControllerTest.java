package za.ac.cput.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private final String BASE_URL = "http://localhost:9090/order";

    private static Order order;

    @BeforeAll
    public static void setup() {
        // Initialize Customer objects
        Customer customer = CustomerFactory.buildCustomer( "Liphelo", "Notutela", "nopesh@gmail.com", "0724437650", "Notutela@00");

        // Initialize Venue objects
        Venue venue = VenueFactory.createVenue( "Rondebosch Gardens", "123 Street", 500);

        // Initialize FoodItem objects
        FoodItem foodItem = FoodItemFactory.buildFoodItem( "Burger", "Cheese Burger", 50.00);

        // Initialize Promo objects
        Promo promo = PromoFactory.buildPromo( "PROMO2024", 10.00, new Date()); // Use the current date for expiry

        // Initialize Order objects for testing
        order = OrderFactory.createOrder(3, customer, 150.00, LocalDate.now(), venue, foodItem, promo);
    }


    @Test
    void create() {
        String url = BASE_URL + "/create";
        ResponseEntity<Order> postResponse = restTemplate.postForEntity(url, order, Order.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Order orderSaved = postResponse.getBody();
        assertNotNull(orderSaved.getOrderID());
        order = orderSaved;
        System.out.println("Saved data: " + orderSaved);
    }

    @Test
    void read() {
        String url = BASE_URL + "/read/" + order.getOrderID();
        System.out.println("URL: " + url);
        ResponseEntity<Order> response = restTemplate.getForEntity(url, Order.class);
        assertNotNull(response.getBody());
        assertEquals(order.getOrderID(), response.getBody().getOrderID());
        System.out.println("Read: " + response.getBody());
    }

    @Test
    void update() {
        String url = BASE_URL + "/update";
        Order updatedOrder = new Order.Builder().copy(order)
                .setTotalAmount(175.00)
                .build();
        ResponseEntity<Order> postResponse = restTemplate.postForEntity(url, updatedOrder, Order.class);
        assertNotNull(postResponse);
        assertNotNull(postResponse.getBody());
        Order orderUpdated = postResponse.getBody();
        assertEquals(updatedOrder.getOrderID(), orderUpdated.getOrderID());
        assertEquals(175.00, orderUpdated.getTotalAmount());
        System.out.println("Update: " + postResponse.getBody());
    }

    @Test
    void delete() {
        String url = BASE_URL + "/delete/" + order.getOrderID();
        System.out.println("URL: " + url);
        restTemplate.delete(url);
        System.out.println("Success: Deleted Order");
    }

    @Test
    void getAll() {
        String url = BASE_URL + "/getAll";
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        System.out.println("Show ALL: ");
        System.out.println(response);
        System.out.println(response.getBody());
    }
}
