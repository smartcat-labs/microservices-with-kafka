package io.smartcat.kafka.commons.configuration;

import java.util.Map;
import java.util.Properties;

public abstract class KafkaProperties {

    protected Properties properties;

    public Properties properties() {
        return properties;
    }

    public void add(final String key, final String value) {
        properties.put(key, value);
    }

    public void setProperties(final Map<String, String> properties) {
        this.properties = new Properties(properties.size());
        this.properties.putAll(properties);
    }
}
