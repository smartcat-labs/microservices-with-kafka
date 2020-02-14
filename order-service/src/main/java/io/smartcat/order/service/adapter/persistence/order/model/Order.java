package io.smartcat.order.service.adapter.persistence.order.model;

import io.smartcat.order.service.adapter.persistence.item.model.Item;

import java.util.List;

public class Order {

    private String id;
    private String userId;
    private List<Item> items;
    private Double price;

    public Order(final String id, final String userId, final List<Item> items) {
        this.id = id;
        this.userId = userId;
        this.items = items;
        this.price = items.stream().map(Item::getPrice).reduce(0d, Double::sum);
    }

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(final String userId) {
        this.userId = userId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(final List<Item> items) {
        this.items = items;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }
}
