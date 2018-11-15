package uk.co.lewisvince.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import uk.co.lewisvince.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
}
