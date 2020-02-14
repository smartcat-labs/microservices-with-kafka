package io.smartcat.web.shop.service.domain.cart;

import io.smartcat.avro.events.item.ItemEvent;

public interface ItemEvenPublisher {

    /**
     *  Try to publish {@link ItemEvent} to topic.
     *
     * @param itemEvent that should be published to topic.
     */
    void publishItemEvent(ItemEvent itemEvent);
}
