package io.smartcat.web.shop.service.adapter.kafka.producer.item;

import io.smartcat.avro.events.item.ItemEvent;
import io.smartcat.kafka.commons.KafkaAvroProducer;
import io.smartcat.web.shop.service.adapter.kafka.KafkaChannels;
import io.smartcat.web.shop.service.domain.cart.ItemEvenPublisher;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemEventProducer extends KafkaAvroProducer<String, ItemEvent> implements ItemEvenPublisher {

    private final KafkaChannels channels;

    @Autowired
    public ItemEventProducer(final ItemEventProducerConfiguration configuration, final KafkaChannels channels) {
        super(configuration);
        this.channels = channels;
    }

    @Override
    public void publishItemEvent(final ItemEvent itemEvent) {
        producer().send(new ProducerRecord<>(channels.itemChannel(), itemEvent.getUserId(), itemEvent));
    }
}
