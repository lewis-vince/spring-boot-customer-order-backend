package uk.co.lewisvince.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import uk.co.lewisvince.orderservice.model.Order;
import uk.co.lewisvince.orderservice.repository.OrderRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @RequestMapping(value = "getAll", method = RequestMethod.GET)
    public List<Order> getAll() {
        return orderRepository.findAll();
    }


    @RequestMapping(value = "getByCustomer/{customerId}", method = RequestMethod.GET)
    public List<Order> getByCustomerId(@PathVariable String customerId) {
        return orderRepository.findAllByCustomerId(customerId);
    }

    @RequestMapping(value = "get/{orderId}", method = RequestMethod.GET)
    public Optional<Order> get(@PathVariable String orderId) {
        return orderRepository.findById(orderId);
    }

    @RequestMapping(value = "order", method = RequestMethod.POST)
    public Order create(@RequestBody Order order) {
        return orderRepository.save(order);
    }

    @RequestMapping(value = "delete/{orderId}", method = RequestMethod.DELETE)
    public void delete(@PathVariable String orderId) {
        orderRepository.deleteById(orderId);
    }
}
