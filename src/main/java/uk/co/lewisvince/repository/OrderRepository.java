package uk.co.lewisvince.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uk.co.lewisvince.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
