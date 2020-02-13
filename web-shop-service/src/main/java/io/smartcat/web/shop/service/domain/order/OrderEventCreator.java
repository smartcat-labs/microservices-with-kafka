package io.smartcat.web.shop.service.domain.order;

import io.smartcat.avro.events.order.OrderEvent;
import io.smartcat.avro.events.order.OrderEventType;
import org.springframework.stereotype.Service;

@Service
public class OrderEventCreator {

    public OrderEvent orderEvent(final String userId, final OrderEventType eventType) {
        return OrderEvent.newBuilder()
                .setEventType(eventType)
                .setTimestamp(System.currentTimeMillis())
                .setUserId(userId)
                .build();
    }
}
