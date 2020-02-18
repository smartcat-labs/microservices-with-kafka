package io.smartcat.order.service.domain.order;

import io.smartcat.order.service.domain.item.Item;

import java.util.List;

public interface OrderRepository {

    Order create(String userId, List<Item> items);
}
