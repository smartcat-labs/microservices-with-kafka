package io.smartcat.order.service.adapter.persistence.order;

import io.smartcat.order.service.adapter.persistence.item.model.Item;
import io.smartcat.order.service.adapter.persistence.order.model.Order;
import io.smartcat.order.service.domain.repository.OrderRepository;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public class OrderRepositoryMock implements OrderRepository {

    private final Map<String, Order> orders;

    public OrderRepositoryMock() {
        orders = new HashMap<>();
    }

    @Override
    public Order create(final String userId, final List<Item> items) {
        final Order order = new Order(UUID.randomUUID().toString(), userId, items);
        orders.put(order.getId(), order);
        return order;
    }
}
