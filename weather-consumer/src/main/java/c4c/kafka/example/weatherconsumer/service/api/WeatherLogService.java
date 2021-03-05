package c4c.kafka.example.weatherconsumer.service.api;

import c4c.kafka.example.weatherconsumer.dto.WeatherLog;

public interface WeatherLogService {
    void saveLogs(WeatherLog log) throws InterruptedException;
}
