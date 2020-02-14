package io.smartcat.order.service;

import io.confluent.kafka.serializers.AvroSchemaUtils;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.streams.serdes.avro.GenericAvroDeserializer;
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import io.smartcat.avro.events.item.ItemAvro;
import io.smartcat.avro.events.item.ItemEvent;
import io.smartcat.avro.events.item.ItemEventType;
import io.smartcat.avro.events.order.OrderEvent;
import io.smartcat.avro.events.order.OrderEventType;
import io.smartcat.order.service.adapter.kafka.consumer.item.ItemEventConsumer;
import io.smartcat.order.service.adapter.kafka.producer.item.ItemEventProducer;
import io.smartcat.order.service.adapter.kafka.producer.order.OrderEventProducer;
import io.smartcat.order.service.adapter.persistence.item.model.Item;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class Application {

	public static void main(final String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			final ItemEventConsumer itemEventConsumer,
			final ItemEventProducer itemEventProducer,
			final OrderEventProducer orderEventProducer
	) {
		return args -> {
/*			for (int i = 0; i < 5; i++) {
				final String id = "" + i;
				final ItemEvent itemEvent = new ItemEvent(
						ItemEventType.ADD_ITEM,
						System.currentTimeMillis(),
						"user1",
						new ItemAvro(id, "category_" + i, "name_" + i, 10d)
				);
				itemEventProducer.producer().send(new ProducerRecord<>("item", "user1", itemEvent));
			}
			final OrderEvent orderEvent = new OrderEvent(OrderEventType.ADD_ORDER, System.currentTimeMillis(), "user1");
			orderEventProducer.producer().send(new ProducerRecord<>("order", "user1", orderEvent));*/
			Properties props = new Properties();
			props.put(StreamsConfig.APPLICATION_ID_CONFIG, "item.id2");
			props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
			props.put("schema.registry.url", "http://my-schema-registry:8081");


			Map<String, String> map = Map.of("schema.registry.url", "http://localhost:8081");
			SpecificAvroSerde<ItemEvent> specificRecordSpecificAvroSerde = new SpecificAvroSerde<>();
			specificRecordSpecificAvroSerde.configure(map, false);

			Serde<String> string = Serdes.String();
			string.configure(map, false);

			props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, string.getClass().getName());
			props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, specificRecordSpecificAvroSerde.getClass().getName());

			final StreamsBuilder builder = new StreamsBuilder();

			KStream<String, ItemEvent> source = builder
					.stream("item", Consumed.with(string, specificRecordSpecificAvroSerde));
			source.foreach((k, v) -> System.out.println(v.getUserId()));


			final Topology topology = builder.build();

			final KafkaStreams streams = new KafkaStreams(topology, props);
			final CountDownLatch latch = new CountDownLatch(1);

			// attach shutdown handler to catch control-c
			Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
				@Override
				public void run() {
					streams.close();
					latch.countDown();
				}
			});

			try {
				streams.start();
				latch.await();
			} catch (Throwable e) {
				System.exit(1);
			}
			System.exit(0);
		};
	}
}
