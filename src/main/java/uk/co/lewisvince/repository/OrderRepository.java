package uk.co.lewisvince.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.co.lewisvince.model.Order;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, Long> {
//    List<Order> findByCustomerId(long customerId);
}
