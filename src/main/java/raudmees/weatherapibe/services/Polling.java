package raudmees.weatherapibe.services;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import raudmees.weatherapibe.models.City;
import raudmees.weatherapibe.models.RecordPoint;
import raudmees.weatherapibe.repositories.CityRepository;
import raudmees.weatherapibe.repositories.RecordPointRepository;

@Service
public class Polling {

    @Autowired
    private WeatherAPI weatherAPI;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RecordPointRepository recordPointRepository;

    @Scheduled(cron = "0 */15 * * * *")
    public void pollCityWeather() {

        try {
            var cities = cityRepository.findAll();
            ObjectMapper mapper = new ObjectMapper();
            for (City city: cities) {
                var cityName = city.getName();
                var weather = weatherAPI.getWeather(cityName);

                RecordPoint recordPoint = mapper.readValue(weather.toString(), new TypeReference<>() {
                });

                recordPoint.setCity(city);

                recordPointRepository.saveAndFlush(recordPoint);
            }

        }
        catch (Exception error) {
            error.printStackTrace();
        }

    }

}
