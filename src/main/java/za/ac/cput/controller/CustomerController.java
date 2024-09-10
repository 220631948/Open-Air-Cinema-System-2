package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.LoginRequest;
import za.ac.cput.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:4200", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = {"Authorization", "Content-Type", "Accept"})
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public Customer create(@RequestBody Customer customer) {
        return customerService.create(customer);
    }

    @GetMapping("/read/{customerId}")
    public Customer read(@PathVariable int customerId) {
        return customerService.read(customerId);
    }

    @PostMapping("/update")
    public Customer update(@RequestBody Customer customer) {
        return customerService.update(customer);
    }

    @GetMapping("/getAll")
    public List<Customer> getAllCustomers() {
        return customerService.getAll();
    }

    @PostMapping("/login")
    public Customer login(@RequestBody LoginRequest loginRequest) {
        return customerService.login(loginRequest.getEmail(), loginRequest.getPassword());
    }
}
