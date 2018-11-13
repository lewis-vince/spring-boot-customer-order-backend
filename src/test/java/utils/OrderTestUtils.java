package utils;

import uk.co.lewisvince.model.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderTestUtils {
    public static List<Order> generateOrderList() {
        Order order1 = new Order(3, new Date(), new ArrayList<>());
        Order order2 = new Order(1, new Date(), new ArrayList<>());

        return Arrays.asList(order1, order2);
    }
}
