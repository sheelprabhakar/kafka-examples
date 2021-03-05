package c4c.kafka.example.weatherconsumer.service.impl;

import c4c.kafka.example.weatherconsumer.dto.WeatherLog;
import c4c.kafka.example.weatherconsumer.repository.WeatherRepository;
import c4c.kafka.example.weatherconsumer.service.api.WeatherLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherLogServiceImpl implements WeatherLogService {
    private final WeatherRepository repository;

    @Autowired
    WeatherLogServiceImpl(final WeatherRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveLogs(WeatherLog log) throws InterruptedException {
       // this.logQue.put(log);
        this.repository.save(log);
    }

}
