package c4c.kafka.example.weatheragent.repository;

import c4c.kafka.example.weatheragent.dto.City;
import org.springframework.data.repository.CrudRepository;

public interface CityRepository extends CrudRepository<City, Integer> {

}
