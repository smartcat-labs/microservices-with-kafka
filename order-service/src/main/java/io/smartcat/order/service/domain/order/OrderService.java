package io.smartcat.order.service.domain.order;


import io.smartcat.order.service.domain.item.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    final OrderRepository orderRepository;

    @Autowired
    public OrderService(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order create(final String userId, final List<Item>items) {
        return orderRepository.create(userId, items);
    }
}
