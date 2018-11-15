package uk.co.lewisvince.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.lewisvince.model.Customer;
import uk.co.lewisvince.repository.CustomerRepository;
import utils.CustomerTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CustomerControllerTest {

    @InjectMocks
    private CustomerController customerController;

    @Mock
    CustomerRepository customerRepository;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllCustomersRequestReturnsCorrectCustomers() {
        List<Customer> customers = CustomerTestUtils.generateCustomerList();
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> customerResponse = customerController.getAll();
        assertEquals(customers, customerResponse);
    }
}
