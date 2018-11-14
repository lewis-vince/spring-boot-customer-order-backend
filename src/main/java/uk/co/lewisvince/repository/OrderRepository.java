package uk.co.lewisvince.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.co.lewisvince.model.Order;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findAllByCustomerId(String customerId);
}
