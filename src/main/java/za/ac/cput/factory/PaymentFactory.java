package za.ac.cput.factory;

import za.ac.cput.domain.Booking;
import za.ac.cput.domain.Payment;
import za.ac.cput.utils.HelperUtils;

public class PaymentFactory {

    // Builder pattern to create Payment objects

    public static Payment buildPayment(double amount, String paymentMethod, Booking booking) {
        if (HelperUtils.isNullorEmpty(amount) || HelperUtils.isNullorEmpty(paymentMethod) || HelperUtils.isNullorEmpty(booking))
            return null;

        return new Payment.Builder()
                .setAmount(amount)
                .setPaymentMethod(paymentMethod)
                .setBooking(booking)
                .build();
    }

    public static Payment buildPayment(double amount) {
        if (HelperUtils.isNullorEmpty(amount))
            return null;

        return new Payment.Builder()
                .setAmount(amount)
                .build();
    }

    public static Payment buildPayment(double amount, String paymentMethod) {
        if (HelperUtils.isNullorEmpty(amount) || HelperUtils.isNullorEmpty(paymentMethod))
            return null;

        return new Payment.Builder()
                .setAmount(amount)
                .setPaymentMethod(paymentMethod)
                .build();
    }
}