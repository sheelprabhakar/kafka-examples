package c4c.kafka.example.weatherconsumer;

import c4c.kafka.example.weatherconsumer.dto.WeatherInfo;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }


   @KafkaListener(topics = "weather-log2", groupId = "group-1", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(ConsumerRecord<String, String> record){
        System.out.println("Received Message in group foo: " + record.value());
    }
}
