package za.ac.cput.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.domain.Order;
import za.ac.cput.repository.IOrderRepository;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    private final IOrderRepository orderRepository;

    @Autowired
    public OrderService(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order create(Order order) {
        return this.orderRepository.save(order);
    }

    @Override
    public Order read(Integer id) {
        return this.orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order update(Order order) {
        return this.orderRepository.existsById(order.getOrderID()) ? this.orderRepository.save(order) : null;
    }

    @Override
    public boolean delete(Integer id) {
        if (this.orderRepository.existsById(id)) {
            this.orderRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Order> getAll() {
        return this.orderRepository.findAll();
    }
}
