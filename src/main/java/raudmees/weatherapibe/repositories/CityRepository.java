package raudmees.weatherapibe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raudmees.weatherapibe.models.City;

public interface CityRepository extends JpaRepository<City, Long> {
}
