package io.smartcat.web.shop.service.adapter.kafka.producer.order;

import io.smartcat.avro.events.order.OrderEvent;
import io.smartcat.kafka.commons.KafkaAvroProducer;
import io.smartcat.web.shop.service.adapter.kafka.KafkaChannels;
import io.smartcat.web.shop.service.domain.cart.OrderEventPublisher;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderEventProducer extends KafkaAvroProducer<String, OrderEvent> implements OrderEventPublisher {

    private final KafkaChannels channels;

    @Autowired
    public OrderEventProducer(final OrderEventProducerConfiguration configuration, final KafkaChannels channels) {
        super(configuration);
        this.channels = channels;
    }

    @Override
    public void publishOrderEvent(final OrderEvent orderEvent) {
        producer().send(new ProducerRecord<>(channels.orderChannel(), orderEvent.getUserId(), orderEvent));
    }
}
