package c4c.kafka.example.weatherconsumer.config;

import c4c.kafka.example.weatherconsumer.dto.WeatherInfo;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Predicate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration;
import org.springframework.kafka.config.KafkaStreamsConfiguration;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerde;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableKafkaStreams
public class KafkaStreamConfig {
    @Value("${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value("${kafka.groupId}")
    private String groupId;

    @Bean(name = KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME)
    public KafkaStreamsConfiguration streamsConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapAddress);
        props.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        props.put(
                StreamsConfig.APPLICATION_ID_CONFIG,
                "stream-1-weather");

        JsonSerde<WeatherInfo> jsonSerde = new JsonSerde<>(WeatherInfo.class);

        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, jsonSerde.getClass());

        return new KafkaStreamsConfiguration(props);
    }

    @Bean
    public KStream<String, WeatherInfo> kStreamJson(StreamsBuilder builder) {
        JsonDeserializer<WeatherInfo> deserializer = new JsonDeserializer<>(WeatherInfo.class);
        deserializer.setRemoveTypeHeaders(false);
        deserializer.addTrustedPackages("*");
        deserializer.setUseTypeMapperForKey(true);

        JsonSerde<WeatherInfo> jsonSerde = new JsonSerde<>(new JsonSerializer<>(), deserializer);
        KStream<String, WeatherInfo> stream = builder.stream("extreme-weather",
                Consumed.with(Serdes.String(), jsonSerde));

        KStream<String, WeatherInfo> filter = stream.filter(new Predicate<String, WeatherInfo>() {
            @Override
            public boolean test(String key, WeatherInfo value) {
                return value.getTemp() > 43 || value.getTemp() < -13;
            }
        });

        //Perform an action on each record of KStream.
        filter.peek(new ForeachAction<String, WeatherInfo>() {
            @Override
            public void apply(String key, WeatherInfo value) {
                System.out.println("Inside filter " + value.getCity());
            }
        });

        filter.to("extreme-weather-filter");

        return stream;
    }
}