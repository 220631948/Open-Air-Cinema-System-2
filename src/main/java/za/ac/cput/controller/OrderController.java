package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.*;
import za.ac.cput.service.*;

import java.util.List;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = {"Authorization", "Content-Type", "Accept"})
public class OrderController {

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

    @PostMapping("/create")
    public Order create(@RequestBody Order order) {
        // Fetch the existing customer, venue, food item, and promo from the database
        Customer existingCustomer = customerService.read(order.getCustomer().getCustomerId());
        if (existingCustomer == null) {
            throw new RuntimeException("Customer not found");
        }

        Venue existingVenue = venueService.read(order.getVenue().getVenueId());
        if (existingVenue == null) {
            throw new RuntimeException("Venue not found");
        }

        FoodItem existingFoodItem = foodItemService.read(order.getFoodItem().getFoodItemID());
        if (existingFoodItem == null) {
            throw new RuntimeException("Food item not found");
        }

        Promo existingPromo = promoService.read(order.getPromo().getPromoID());
        if (existingPromo == null) {
            throw new RuntimeException("Promo not found");
        }

        // Build the order with the existing entities
        Order newOrder = new Order.Builder()
                .copy(order)
                .setCustomer(existingCustomer)
                .setVenue(existingVenue)
                .setFoodItem(existingFoodItem)
                .setPromo(existingPromo)
                .build();

        return orderService.create(newOrder);
    }

    @GetMapping("/read/{orderId}")
    public Order read(@PathVariable int orderId) {
        return orderService.read(orderId);
    }

    @PostMapping("/update")
    public Order update(@RequestBody Order order) {
        // Fetch the existing customer, venue, food item, and promo from the database
        Customer existingCustomer = customerService.read(order.getCustomer().getCustomerId());
        if (existingCustomer == null) {
            throw new RuntimeException("Customer not found");
        }

        Venue existingVenue = venueService.read(order.getVenue().getVenueId());
        if (existingVenue == null) {
            throw new RuntimeException("Venue not found");
        }

        FoodItem existingFoodItem = foodItemService.read(order.getFoodItem().getFoodItemID());
        if (existingFoodItem == null) {
            throw new RuntimeException("Food item not found");
        }

        Promo existingPromo = promoService.read(order.getPromo().getPromoID());
        if (existingPromo == null) {
            throw new RuntimeException("Promo not found");
        }

        // Build the updated order with the existing entities
        Order updatedOrder = new Order.Builder()
                .copy(order)
                .setCustomer(existingCustomer)
                .setVenue(existingVenue)
                .setFoodItem(existingFoodItem)
                .setPromo(existingPromo)
                .build();

        return orderService.update(updatedOrder);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable int id) {
        orderService.delete(id);
    }

    @GetMapping("/getAll")
    public List<Order> getAll() {
        return orderService.getAll();
    }
}

