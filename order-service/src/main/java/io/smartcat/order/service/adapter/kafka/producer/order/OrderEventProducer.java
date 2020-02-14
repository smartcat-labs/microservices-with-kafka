package io.smartcat.order.service.adapter.kafka.producer.order;

import io.smartcat.avro.events.order.OrderEvent;
import io.smartcat.kafka.commons.KafkaAvroProducer;
import io.smartcat.order.service.adapter.kafka.KafkaChannels;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer extends KafkaAvroProducer<String, OrderEvent> {

    private final KafkaChannels channels;

    @Autowired
    public OrderEventProducer(final OrderEventProducerConfiguration configuration, final KafkaChannels channels) {
        super(configuration);
        this.channels = channels;
    }
}
