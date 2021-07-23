package raudmees.weatherapibe.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raudmees.weatherapibe.models.City;
import raudmees.weatherapibe.models.RecordPoint;

import java.util.List;


public interface RecordPointRepository extends JpaRepository<RecordPoint, Long> {
    List<RecordPoint> findAllByCityOrderByTimeDesc(City city);

}
