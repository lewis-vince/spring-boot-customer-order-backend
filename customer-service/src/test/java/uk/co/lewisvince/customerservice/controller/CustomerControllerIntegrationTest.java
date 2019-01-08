package uk.co.lewisvince.customerservice.controller;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.co.lewisvince.customerservice.model.Customer;
import utils.migration.MongoTestMigrationUtils;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@TestPropertySource(locations = "classpath:application-test.properties")
public class CustomerControllerIntegrationTest {

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
    public void getCustomersReturnsOkWithCorrectCustomers() {
        ResponseEntity<Customer[]> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/customers/getAll",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Customer[]>() {
                });

        assertEquals("Response status is 200 (OK)",
                HttpStatus.OK, response.getStatusCode());
        assertNotNull("Response body is not null", response.getBody());

        assertEquals(3, response.getBody().length);

        // check fields in response customers
        Assert.assertEquals("HEIN_000003", response.getBody()[2].getId());
    }
}
