package uk.co.lewisvince.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "orders")
public class Order {
    @Id
    private ObjectId id;
    private Integer customerId;
    private Date orderDate;
//    @JsonProperty
//    private List<OrderItem> items;

    public Order() {
//        items = new ArrayList<>();
    }

    public Order(int customerId, Date orderDate, List<OrderItem> items) {
        this.customerId = customerId;
        this.orderDate = orderDate;
//        this.items = items;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
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
