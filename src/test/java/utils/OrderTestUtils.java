package utils;

import uk.co.lewisvince.model.Order;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderTestUtils {
    public static List<Order> generateOrderList() {
        Order order1 = new Order();
        order1.setCustomerId(3);
        order1.setOrderDate(new Date());

        Order order2 = new Order();
        order1.setCustomerId(1);
        order1.setOrderDate(new Date());

        return Arrays.asList(order1, order2);
    }
}
