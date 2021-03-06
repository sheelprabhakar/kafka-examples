package c4c.kafka.example.weatherconsumer;

import c4c.kafka.example.weatherconsumer.dto.WeatherInfo;
import c4c.kafka.example.weatherconsumer.dto.WeatherLog;
import c4c.kafka.example.weatherconsumer.service.api.WeatherLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.math.BigDecimal;

@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    private WeatherLogService weatherLogService;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @KafkaListener(topics = "weather-log2", groupId = "group-1", containerFactory = "kafkaListenerContainerFactory")
    public void listenGroupFoo(WeatherInfo record) {
        System.out.println("Received Message in group foo: " + record.getTemp());
        try {
            weatherLogService.saveLogs(new WeatherLog(record.getCity(), record.getLatitude(), record.getLongitude()
                    , BigDecimal.valueOf(record.getTemp()), record.getLogDate()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
