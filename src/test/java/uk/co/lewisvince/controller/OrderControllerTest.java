package uk.co.lewisvince.controller;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import uk.co.lewisvince.model.Order;
import uk.co.lewisvince.repository.OrderRepository;
import utils.OrderTestUtils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

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
}
