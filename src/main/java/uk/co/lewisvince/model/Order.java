package uk.co.lewisvince.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private ObjectId id;
    private String customerId;
    private Date orderDate;
//    private List<OrderItem> items;

    public Order() {
//        items = new ArrayList<>();
    }

    public Order(String orderId, String customerId, Date orderDate, List<OrderItem> items) {
        this.id = new ObjectId(orderId);
        this.customerId = customerId;
        this.orderDate = orderDate;
//        this.items = items;
    }

    public String getId() {
        return id.toHexString();
    }

    public void setOrderId(String orderId) {
        this.id = new ObjectId(orderId);
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

//    public List<OrderItem> getItems() {
//        return items;
//    }
//
//    public void setItems(List<OrderItem> items) {
//        this.items = items;
//    }

}
