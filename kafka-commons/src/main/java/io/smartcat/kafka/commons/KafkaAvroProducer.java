package io.smartcat.kafka.commons;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.smartcat.kafka.commons.configuration.KafkaAvroProducerConfiguration;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaAvroProducer<K, V> {

    private static final String KEY_SERIALIZER = StringSerializer.class.getCanonicalName();
    private static final String VALUE_SERIALIZER = KafkaAvroSerializer.class.getCanonicalName();

    protected final KafkaAvroProducerConfiguration configuration;
    protected final KafkaProducer<K, V> producer;

    public KafkaAvroProducer(final KafkaAvroProducerConfiguration configuration) {
        configuration.add(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, KEY_SERIALIZER );
        configuration.add(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, VALUE_SERIALIZER);
        this.configuration = configuration;
        producer = new KafkaProducer<>(configuration.properties());
    }

    public KafkaProducer<K, V> producer() {
        return producer;
    }
}
