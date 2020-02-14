package io.smartcat.order.service.adapter.kafka.producer.item;

import io.smartcat.avro.events.item.ItemEvent;
import io.smartcat.kafka.commons.KafkaAvroProducer;
import io.smartcat.order.service.adapter.kafka.KafkaChannels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemEventProducer extends KafkaAvroProducer<String, ItemEvent> {

    private final KafkaChannels channels;

    @Autowired
    public ItemEventProducer(final ItemEventProducerConfiguration configuration, final KafkaChannels channels) {
        super(configuration);
        this.channels = channels;
    }
}
