package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;

import java.time.LocalDate;
import java.util.Date;

public class OrderFactory {
    public static Order createOrder(int orderID, Customer customer, double totalAmount, LocalDate orderDate, Venue venue, FoodItem foodItem, Promo promo) {
        if (Helper.isNegative(orderID) || customer == null || Helper.isNegative(totalAmount) || orderDate == null || venue == null || foodItem == null || promo == null) {
            return null;
        }

        return new Order.Builder()
                .setOrderID(orderID)
                .setCustomer(customer)
                .setTotalAmount(totalAmount)
                .setOrderDate(orderDate)
                .setVenue(venue)
                .setFoodItem(foodItem)
                .setPromo(promo)
                .build();
    }
}


