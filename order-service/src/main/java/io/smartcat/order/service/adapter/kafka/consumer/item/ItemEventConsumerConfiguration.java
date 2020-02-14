package io.smartcat.order.service.adapter.kafka.consumer.item;

import io.smartcat.kafka.commons.configuration.KafkaAvroConsumerConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("kafka.consumers.item")
public class ItemEventConsumerConfiguration extends KafkaAvroConsumerConfiguration {
}
