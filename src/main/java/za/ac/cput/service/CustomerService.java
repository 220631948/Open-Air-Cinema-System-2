package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Customer;
import za.ac.cput.repository.ICustomerRepository;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {
    private final ICustomerRepository repository;

    @Autowired
    public CustomerService(ICustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Customer create(Customer customer) {
        if (customer != null) {
            return this.repository.save(customer);
        } else {
            throw new IllegalArgumentException("Customer cannot be null");
        }
    }

    @Override
    public Customer read(Integer id) {
        return this.repository.findById(id).orElse(null);
    }

    @Override
    public Customer update(Customer customer) {
        if (this.repository.existsById(customer.getCustomerId())) {
            return this.repository.save(customer);
        }
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        if (this.repository.existsById(id)) {
            this.repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Customer> getAll() {
        return this.repository.findAll();
    }



    public Customer login(String email, String password) {
        List<Customer> customers = repository.findAll(); // Retrieve all customers
        for (Customer customer : customers) {
            if (customer.getEmail().equals(email) && customer.getPassword().equals(password)) {
                return customer; // Return the customer if email and password match
            }
        }
        throw new RuntimeException("Invalid email or password");
    }
}

