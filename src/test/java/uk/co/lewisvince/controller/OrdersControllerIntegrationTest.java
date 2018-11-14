package uk.co.lewisvince.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.lewisvince.model.Order;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrdersControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getOrdersReturnsOkWithCorrectOrders() {
        ResponseEntity<Order[]> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/orders/getAll",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Order[]>() {
                });

        assertEquals("Response status is 200 (OK)",
                HttpStatus.OK, response.getStatusCode());
        assertNotNull("Response body is not null", response.getBody());

        assertEquals(3, response.getBody().length);
    }

    @Test
    public void getOrderWithCustomerIdReturnsAllMatchingOrders() {
        ResponseEntity<List<Order>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/orders/getByCustomer/3",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Order>>() {});

        assertEquals("Response status is 200 (OK)",
                HttpStatus.OK, response.getStatusCode());


        assertNotNull("Response body is not null", response.getBody());

        assertEquals("Number of retrieved orders is correct",
                1, response.getBody().size());
        assertEquals("Customer id in the retrieved orders matches",
                "3", response.getBody().get(0).getCustomerId());
    }

    @Test
    public void getOrderByIdReturnsTheCorrectOrder() {
        String requestedOrderId = "5beacfce0c8c140c60c5f402";
        String expectedCustomerId = "1";

        ResponseEntity<Order> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/orders/get/" + requestedOrderId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Order>() {});

        assertEquals("Response status is 200 (OK)",
                HttpStatus.OK, response.getStatusCode());
        assertNotNull("Response body is not null", response.getBody());

        assertEquals("Retrieved order has matching id to request",
                requestedOrderId, response.getBody().getId());
        assertEquals("Retrieved order has correct customer",
                expectedCustomerId, response.getBody().getCustomerId());
    }

    @Test
    public void addOrderWithCorrectOrderDetailsAddsTheOrderToTheDb() {

    }
}
