package raudmees.weatherapibe.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raudmees.weatherapibe.models.City;
import raudmees.weatherapibe.models.RecordPoint;
import raudmees.weatherapibe.repositories.CityRepository;
import raudmees.weatherapibe.repositories.RecordPointRepository;
import raudmees.weatherapibe.services.WeatherAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/weather")
public class CityRestController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RecordPointRepository recordPointRepository;

    @Autowired
    private WeatherAPI weatherAPI;

    @GetMapping("{city}")
    public Object getCityWeather(@PathVariable String city) throws Exception {
      return weatherAPI.getWeather(city);
    }

    @PostMapping
    public RecordPoint create(@RequestBody final RecordPoint recordPoint) {

        City city = new City();
        city.setName(recordPoint.getCityName());
        var savedCity = cityRepository.saveAndFlush(city);
        recordPoint.setCity(savedCity);

        return recordPointRepository.saveAndFlush(recordPoint);
    }

    @GetMapping
    public ResponseEntity<ArrayList<Object>> list() {

        List<City> cities = cityRepository.findAll();
        ArrayList<Object> parent = new ArrayList<>();

        for(City city: cities) {
            Map<String, Object> object = new HashMap<>();
            object.put("name", city.getName());
            object.put("id", city.getCityId());
            List<RecordPoint> recordPoints = recordPointRepository.findAllByCityOrderByTimeDesc(city);
            object.put("recordPoints", recordPoints);
            parent.add(object);
        }

        return ResponseEntity.ok().body(parent);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        cityRepository.deleteById(id);
    }
}
