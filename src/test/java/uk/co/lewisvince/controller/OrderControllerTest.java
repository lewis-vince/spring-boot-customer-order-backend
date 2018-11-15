package uk.co.lewisvince.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.lewisvince.model.Order;
import uk.co.lewisvince.repository.OrderRepository;
import utils.OrderTestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class OrderControllerTest {

    @InjectMocks
    private OrderController orderController;

    @Mock
    OrderRepository orderRepository;

    @Before
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllOrdersRequestReturnsCorrectOrders() {
        List<Order> orders = OrderTestUtils.generateOrderList();
        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> ordersResponse = orderController.getAll();
        assertEquals(orders, ordersResponse);
    }

    @Test
    public void getOrderByCustomerIdReturnsRelevantOrders() {
        List<Order> orders = OrderTestUtils.generateOrderList("3");
        when(orderRepository.findAllByCustomerId("3")).thenReturn(orders);

        List<Order> ordersResponse = orderController.getByCustomerId("3");
        assertEquals(orders, ordersResponse);
    }

    @Test
    public void getOrderByOrderIdReturnsCorrectOrder() {
        String orderId = "5beacfce0c8c140c60c5f402";
        Order testOrder = new Order(orderId, "3", new Date());
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(testOrder));

        Optional<Order> response = orderController.get(orderId);
        assertTrue("Request has returned a value", response.isPresent());
        assertEquals("Returned order is the correct order",
                testOrder.getId(), response.get().getId());
    }

    @Test
    public void addOrderReturnsCorrectOrderResponse() {
        // create dummy order details
        String orderId = "5beacfca0c3c140c60a5f402";
        String customerId = "39";
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.set(2018, Calendar.JULY, 21, 14, 45, 39);
        Date orderDate = calendar.getTime();
        Order testOrder = new Order(orderId, customerId, orderDate);

        when(orderRepository.save(testOrder)).thenReturn(testOrder);

        Order response = orderController.create(testOrder);
        assertEquals("Add order request returns the same order back", testOrder, response);
    }

    @Test
    public void deleteOrderCorrectlyDeletesOrder() {
        // create dummy order details
        String orderId = "TESTORDER";

        // mock delete (void) method
        doNothing().when(orderRepository).deleteById(orderId);

        //call delete method
        orderController.delete(orderId);

        // verify that the delete method in the repository was called once
        // with the correct order parameter
        verify(orderRepository, times(1)).deleteById(orderId);
    }
}

