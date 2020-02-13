package io.smartcat.web.shop.service.adapter.kafka;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties("kafka.channels")
public class KafkaChannels {

    private String itemChannel;
    private String orderChannel;

    public String itemChannel() {
        return itemChannel;
    }

    public void setItemChannel(final String itemChannel) {
        this.itemChannel = itemChannel;
    }

    public String orderChannel() {
        return orderChannel;
    }

    public void setOrderChannel(final String orderChannel) {
        this.orderChannel = orderChannel;
    }
}
