package io.smartcat.order.service.domain.service;


import io.smartcat.order.service.adapter.persistence.item.model.Item;
import io.smartcat.order.service.adapter.persistence.order.model.Order;
import io.smartcat.order.service.domain.repository.OrderRepository;
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
