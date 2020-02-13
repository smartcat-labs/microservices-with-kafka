package io.smartcat.web.shop.service.adapter.kafka.producer.order;

import io.smartcat.kafka.commons.configuration.KafkaAvroProducerConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("kafka.producers.order")
public class OrderEventProducerConfiguration extends KafkaAvroProducerConfiguration {
}
