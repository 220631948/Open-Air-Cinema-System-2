package za.ac.cput.utils;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class HelperUtils {
    public static boolean isNullorEmpty(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static boolean isNullorEmpty(Object value) {
        return value == null;
    }

    public static String randomSeatNumber() {
        char row = (char) ('A' + ThreadLocalRandom.current().nextInt(0, 10));
        int number = ThreadLocalRandom.current().nextInt(1, 20);
        return String.valueOf(row) + number;
    }


    public static boolean isNullorEmpty(double value) {
        return value == 0.0;
    }

    public static boolean isNullorEmpty(Date value) {
        return value == null;
    }


    // is valid payment amount
    public boolean isValidPaymentAmount(double paymentAmount) {
        return paymentAmount > 0;
    }


}