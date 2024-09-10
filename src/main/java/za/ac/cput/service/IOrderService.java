package za.ac.cput.service;

import za.ac.cput.domain.Order;

import java.util.List;

public interface IOrderService {
    Order create(Order order);
    Order read(Integer id);
    Order update(Order order);
    boolean delete(Integer id);
    List<Order> getAll();
}

