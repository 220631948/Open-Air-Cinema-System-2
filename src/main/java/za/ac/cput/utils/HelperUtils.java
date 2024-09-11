package za.ac.cput.utils;

import java.util.Date;

public class HelperUtils {
    public static boolean isNullorEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNullorEmpty(Object value) {
        return value == null;
    }

    public static boolean isNullorEmpty(int value) {
        return value == 0;
    }

    public static boolean isNullorEmpty(double value) {
        return value == 0.0;
    }

    public static boolean isNullorEmpty(Date value) {
        return value == null;
    }

    public static boolean isNullOrEmpty(String ticketID) {
        return ticketID == null || ticketID.trim().isEmpty();
    }

    // is valid payment amount
    public boolean isValidPaymentAmount(double paymentAmount) {
        return paymentAmount > 0;
    }









}