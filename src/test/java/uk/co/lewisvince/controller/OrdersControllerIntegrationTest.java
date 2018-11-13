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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.lewisvince.model.Order;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class OrdersControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getOrdersReturnsOkWithCorrectOrders() {
        ResponseEntity<List<Order>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/orders/getOrders",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Order>>() {});
        List<Order> orders = response.getBody();

        //assert that response is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

}
