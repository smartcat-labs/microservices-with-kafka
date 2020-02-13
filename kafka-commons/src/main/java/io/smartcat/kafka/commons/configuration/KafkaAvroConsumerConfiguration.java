package io.smartcat.kafka.commons.configuration;

import java.time.Duration;

public class KafkaAvroConsumerConfiguration extends KafkaProperties {

    protected String topic;
    protected Duration pollTimeout;
    protected boolean subscribeOnCreate = true;

    public String topic() {
        return topic;
    }

    public void setTopic(final String topic) {
        this.topic = topic;
    }

    public Duration pollTimeout() {
        return pollTimeout;
    }

    public void setPollTimeout(final int pollTimeout) {
        this.pollTimeout = Duration.ofMillis(pollTimeout);
    }

    public boolean subscribeOnCreate() {
        return subscribeOnCreate;
    }

    public void setSubscribeOnCreate(final boolean subscribeOnCreate) {
        this.subscribeOnCreate = subscribeOnCreate;
    }
}
