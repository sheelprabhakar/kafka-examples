package c4c.kafka.example.weatheragent.service.impl;

import c4c.kafka.example.weatheragent.dto.City;
import c4c.kafka.example.weatheragent.dto.WeatherInfo;
import c4c.kafka.example.weatheragent.repository.CityRepository;
import c4c.kafka.example.weatheragent.service.api.WeatherAgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class WeatherAgentServiceImpl implements WeatherAgentService {
    private final CityRepository cityRepository;
    private final ExecutorService executor;
    private final KafkaTemplate<String, WeatherInfo> kafkaTemplate;
    private final Random rand = new Random();
    private final int min = -20;
    private final int max = 50;
    @Autowired
    public WeatherAgentServiceImpl(CityRepository cityRepository, KafkaTemplate<String, WeatherInfo> kafkaTemplate) {
        this.cityRepository = cityRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.executor = Executors.newFixedThreadPool(10);

    }

    @Override
    public void update() {
        Iterable<City> cities = this.cityRepository.findAll();
        int i =0;
        for (City city : cities) {
            float randomNum = min + rand.nextFloat() * (max - min);
            this.executor.submit(new WeatherTask(city, randomNum));
        }
    }

    class WeatherTask implements Callable<Boolean> {
        private final City city;
        private final float temp;

        public WeatherTask(final City city, float temp) {
            this.city = city;
            this.temp = temp;
        }

        @Override
        public Boolean call() throws Exception {
            WeatherInfo info = new WeatherInfo(city.getName(), city.getLatitude(), city.getLongitude(), temp);
           //kafkaTemplate.send("weather-log2",""+info.getLogDate().getTimeInMillis(), info);
            kafkaTemplate.send("extreme-weather",""+info.getLogDate().getTimeInMillis(), info);
            System.out.println(city.getName() + ":" +temp);
            return true;
        }
    }
}
