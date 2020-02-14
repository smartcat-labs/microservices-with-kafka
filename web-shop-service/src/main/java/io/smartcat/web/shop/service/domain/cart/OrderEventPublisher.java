package io.smartcat.web.shop.service.domain.cart;

import io.smartcat.avro.events.order.OrderEvent;

public interface OrderEventPublisher {

    /**
     *  Try to publish {@link OrderEvent} to topic.
     *
     * @param orderEvent that should be published to topic.
     */
    void publishOrderEvent(OrderEvent orderEvent);
}
