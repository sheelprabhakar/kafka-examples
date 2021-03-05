package c4c.kafka.example.weatherconsumer.repository;

import c4c.kafka.example.weatherconsumer.dto.WeatherLog;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface WeatherRepository extends CrudRepository<WeatherLog, Integer> {

}