package io.smartcat.order.service.domain.repository;

import io.smartcat.order.service.adapter.persistence.item.model.Item;
import io.smartcat.order.service.adapter.persistence.order.model.Order;

import java.util.List;

public interface OrderRepository {

    Order create(String userId, List<Item> items);
}
