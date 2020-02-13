package io.smartcat.web.shop.service.domain.cart;

import io.smartcat.avro.events.item.ItemAvro;
import io.smartcat.avro.events.item.ItemEvent;
import io.smartcat.avro.events.item.ItemEventType;
import io.smartcat.web.shop.service.domain.item.Item;
import org.springframework.stereotype.Service;

@Service
public class ItemToItemEventMapper {

    public ItemEvent itemToItemEvent(final String userId, final Item item, final ItemEventType eventType) {
        final ItemAvro itemAvro = ItemAvro.newBuilder()
                .setItemId(item.getId())
                .setCategory(item.getCategory())
                .setName(item.getName())
                .setPrice(item.getPrice())
                .build();

        return ItemEvent.newBuilder()
                .setEventType(eventType)
                .setTimestamp(System.currentTimeMillis())
                .setUserId(userId)
                .setItemAvro(itemAvro)
                .build();
    }
}
