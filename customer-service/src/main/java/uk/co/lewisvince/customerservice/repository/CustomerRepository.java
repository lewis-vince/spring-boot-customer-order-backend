package uk.co.lewisvince.customerservice.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.co.lewisvince.customerservice.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
