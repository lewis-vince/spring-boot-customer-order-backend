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
import uk.co.lewisvince.model.OrderList;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.*;

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
                new ParameterizedTypeReference<Order[]>(){});

        //assert that response is OK
        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(2, response.getBody().length);
    }

}
