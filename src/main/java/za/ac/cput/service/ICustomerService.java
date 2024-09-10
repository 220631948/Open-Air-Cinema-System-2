package za.ac.cput.service;

import za.ac.cput.domain.Customer;
import java.util.List;

public interface ICustomerService {
    Customer create(Customer customer);
    Customer read(Integer id);
    Customer update(Customer customer);
    boolean delete(Integer id);
    List<Customer> getAll();
}

