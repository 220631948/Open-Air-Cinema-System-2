package za.ac.cput.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import za.ac.cput.domain.*;
import za.ac.cput.factory.*;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private VenueService venueService;

    @Autowired
    private FoodItemService foodItemService;

    @Autowired
    private PromoService promoService;

    private static Order order1;
    private static Order order2;

    @BeforeEach
    void setUp() {
        // Initialize Customer objects
        Customer customer1 = CustomerFactory.buildCustomer( "Liphelo", "Notutela", "nopesh@gmail.com", "0724437650", "Notutela@00");
        Customer customer2 = CustomerFactory.buildCustomer( "Akuxolo", "Smith", "akuxolo@gmail.com", "0987654321", "ASmith@2024");

        // Save customers to database
        customerService.create(customer1);
        customerService.create(customer2);

        // Initialize Venue objects
        Venue venue1 = VenueFactory.createVenue( "Glenelly Estate", "Stellenbosch", 500);
        Venue venue2 = VenueFactory.createVenue( "Battery Park", "V&A Waterfront", 300);

        // Save venues to database
        venueService.create(venue1);
        venueService.create(venue2);

        // Initialize FoodItem objects
        FoodItem foodItem1 = FoodItemFactory.buildFoodItem( "Burger", "Cheese Burger", 50.00);
        FoodItem foodItem2 = FoodItemFactory.buildFoodItem( "Pizza", "Pepperoni Pizza", 80.00);

        // Save food items to database
        foodItemService.create(foodItem1);
        foodItemService.create(foodItem2);

        // Initialize Promo objects
        Promo promo1 = PromoFactory.buildPromo( "PROMO10", 10.0, new Date());
        Promo promo2 = PromoFactory.buildPromo( "PROMO20", 20.0, new Date());

        // Save promos to database
        promoService.create(promo1);
        promoService.create(promo2);

        // Initialize Order objects
        order1 = OrderFactory.createOrder(1, customer1, 150.00, LocalDate.now(), venue1, foodItem1, promo1);
        order2 = OrderFactory.createOrder(2, customer2, 200.00, LocalDate.now(), venue2, foodItem2, promo2);

        // Ensure objects are not null
        assertNotNull(order1);
        assertNotNull(order2);
    }

    @Test
    void a_create() {
        Order created1 = orderService.create(order1);
        assertNotNull(created1);
        assertEquals(order1.getOrderID(), created1.getOrderID());
        System.out.println("Created order1: " + created1);

        Order created2 = orderService.create(order2);
        assertNotNull(created2);
        assertEquals(order2.getOrderID(), created2.getOrderID());
        System.out.println("Created order2: " + created2);
    }

    @Test
    void b_read() {
        Order created = orderService.read(1);
        assertNotNull(created);

        Order read = orderService.read(created.getOrderID());
        assertNotNull(read);
        assertEquals(created.getOrderID(), read.getOrderID());
        System.out.println("Read order: " + read);
    }

    @Test
    void c_update() {
        Order createdOrder = orderService.read(1);
        assertNotNull(createdOrder);

        Order updatedOrder = new Order.Builder()
                .copy(createdOrder)       // Copy the existing order
                .setTotalAmount(175.00)    // Update the total amount
                .build();

        // Perform the update operation
        Order updated = orderService.update(updatedOrder);

        // Check if the update was successful
        assertNotNull(updated);
        assertEquals(175.00, updated.getTotalAmount());
        assertEquals(createdOrder.getOrderID(), updated.getOrderID());
        System.out.println("Updated order: " + updated);
    }

    @Test
    void d_getAll() {
        System.out.println(orderService.getAll());
    }
}
