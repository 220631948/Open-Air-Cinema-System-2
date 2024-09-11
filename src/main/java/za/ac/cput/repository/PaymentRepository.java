package za.ac.cput.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import za.ac.cput.domain.Payment;

import java.util.List;

@Repository
public interface PaymentRepository {
    // Find payments by booking ID
    List<Payment> findByBooking_BookingID(int bookingID);

    // Find all payments by payment method
    List<Payment> findByPaymentMethod(String paymentMethod);

    //   // Custom query to find payments within a date range
    @Query("SELECT p FROM Payment p WHERE p.date BETWEEN ?1 AND ?2")
    List<Payment> findPaymentsWithinDateRange(String startDate, String endDate);

    // Find payments with an amount greater than a specific amount
    List<Payment> findByAmountGreaterThan(double amount);

    // Find payments with an amount less than a specific amount
    List<Payment> findByAmountLessThan(double amount);

}