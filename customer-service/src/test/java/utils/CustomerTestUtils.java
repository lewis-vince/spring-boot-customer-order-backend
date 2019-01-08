package utils;

import uk.co.lewisvince.customerservice.model.Customer;

import java.util.Arrays;
import java.util.List;

public class CustomerTestUtils {
    public static List<Customer> generateCustomerList() {
        return Arrays.asList(
                new Customer("CUST_1", "Business 1", "John Smith", "01234 567890", "1 A Road, Town, City, SG19 2JF"),
                new Customer("CUST_2", "Business 2", "Greg Gregson", "04321 567390", "5 A Road, Town, City, SG18 4LP")
        );
    }
}
