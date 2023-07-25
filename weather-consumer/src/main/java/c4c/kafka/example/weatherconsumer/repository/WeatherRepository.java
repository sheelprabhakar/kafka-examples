package c4c.kafka.example.weatherconsumer.repository;

import c4c.kafka.example.weatherconsumer.dto.WeatherLog;
import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;


@Transactional
public interface WeatherRepository extends CrudRepository<WeatherLog, Integer> {

}