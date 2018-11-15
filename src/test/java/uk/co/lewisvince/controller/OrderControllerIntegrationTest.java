package uk.co.lewisvince.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.lewisvince.model.Order;
import utils.migration.MongoTestMigrationUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class OrderControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Before
    public void resetMongoDb() {
        // reset mongodb before each integration test
        try {
            MongoTestMigrationUtils.resetMongoDb(mongoTemplate);
        } catch (IOException e) {
            fail();
        }
    }

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
        String requestedCustomerId = "45";
        ResponseEntity<List<Order>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/orders/getByCustomer/" + requestedCustomerId,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Order>>() {});

        assertEquals("Response status is 200 (OK)",
                HttpStatus.OK, response.getStatusCode());


        assertNotNull("Response body is not null", response.getBody());

        assertEquals("Number of retrieved orders is correct",
                1, response.getBody().size());
        assertEquals("Customer id in the retrieved orders matches",
                requestedCustomerId, response.getBody().get(0).getCustomerId());
    }

    @Test
    public void getOrderByIdReturnsTheCorrectOrder() {
        String requestedOrderId = "ORDER2";
        String expectedCustomerId = "32";

        ResponseEntity<Order> response = restTemplate.getForEntity(
                "http://localhost:" + port + "/api/orders/get/" + requestedOrderId,
                Order.class);

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
        // create dummy order details
        String orderId = "ORDER8";
        String customerId = "13";
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2018, Calendar.MAY, 21, 14, 45, 39);
        Date orderDate = calendar.getTime();
        Order testOrder = new Order(orderId, customerId, orderDate);

        ResponseEntity<Order> response = restTemplate.postForEntity(
                "http://localhost:" + port + "/api/orders/order",
                new HttpEntity<>(testOrder), Order.class);

        assertEquals("Response status is 200 (OK)",
                HttpStatus.OK, response.getStatusCode());
        assertNotNull("Response body is not null", response.getBody());

        assertEquals("Order id matches requested order id",
                orderId, response.getBody().getId());

        // query the db to make sure that the new order has been added correctly
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is("ORDER8"));
        List<Order> dbOrder = mongoTemplate.find(query, Order.class);

        assertEquals("DB contains one matching object", 1, dbOrder.size());
        assertEquals("Order in DB has correct id", orderId, dbOrder.get(0).getId());
    }

    @Test
    public void removeOrderCorrectlyDeletesTheOrderFromTheDb() {
        String targetOrderId = "ORDER3";

        // run DELETE request
        ResponseEntity<Order> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/orders/delete/" + targetOrderId,
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<Order>() {}
        );

        assertEquals("Response status is 200 (OK)",
                HttpStatus.OK, response.getStatusCode());

        // query the db to make sure that the new order has been added correctly
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(targetOrderId));
        List<Order> dbOrder = mongoTemplate.find(query, Order.class);

        assertEquals("DB contains no matching objects", 0, dbOrder.size());
    }
}
