package io.smartcat.web.shop.service.domain.order;

import io.smartcat.web.shop.service.domain.cart.OrderEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static io.smartcat.avro.events.order.OrderEventType.ADD_ORDER;
import static io.smartcat.avro.events.order.OrderEventType.CLEAR_ORDER;

@Service
public class OrderService {

    private final OrderEventPublisher orderEventPublisher;
    private final OrderEventCreator orderEventCreator;

    @Autowired
    public OrderService(final OrderEventPublisher orderEventPublisher, final OrderEventCreator orderEventCreator) {
        this.orderEventPublisher = orderEventPublisher;
        this.orderEventCreator = orderEventCreator;
    }

    public void createAndPublishOrder(final String userId) {
        orderEventPublisher.publishOrderEvent(orderEventCreator.orderEvent(userId, ADD_ORDER));
    }

    public void clearAndPublishOrder(final String userId) {
        orderEventPublisher.publishOrderEvent(orderEventCreator.orderEvent(userId, CLEAR_ORDER));
    }
}
