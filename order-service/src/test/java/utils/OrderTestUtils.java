package utils;



import uk.co.lewisvince.orderservice.model.Order;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class OrderTestUtils {
    public static List<Order> generateOrderList() {
        Order order1 = new Order("5bead50d0c8c130c60c5f403", "3",
                new Date());
        Order order2 = new Order("5beac50d0c8c130c60c5f403", "1",
                new Date());

        return Arrays.asList(order1, order2);
    }

    public static List<Order> generateOrderList(String customerId) {
        Order order1 = new Order("54eac50d0c3c130c60c5f403", customerId,
                new Date());
        Order order2 = new Order("51eac50d0c3c130c60c5f403", "1",
                new Date());

        return Arrays.asList(order1, order2);
    }
}
