package io.smartcat.order.service.adapter.kafka.consumer.item;

import io.smartcat.avro.events.item.ItemEvent;
import io.smartcat.kafka.commons.KafkaAvroConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemEventConsumer extends KafkaAvroConsumer<String, ItemEvent> {

    @Autowired
    public ItemEventConsumer(final ItemEventConsumerConfiguration configuration) {
        super(configuration);
    }
}
