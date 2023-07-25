package c4c.kafka.example.weatheragent;

import c4c.kafka.example.weatheragent.service.api.WeatherAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@SpringBootApplication
@EnableScheduling
public class Application {

    @Autowired
    private WeatherAgentService weatherAgentService;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    //600000
    @Scheduled(fixedRate = 60000, initialDelay = 100)
    public void postWeather() {
        this.weatherAgentService.update();
    }
}
