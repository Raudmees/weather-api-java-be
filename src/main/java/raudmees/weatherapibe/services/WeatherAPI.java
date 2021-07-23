package raudmees.weatherapibe.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.model.CurrentWeather;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class WeatherAPI {

    public Object getWeather(String cityName) throws APIException {

        double temperature;
        double celsius;

        OWM owm = new OWM("19c97c9aca886bd75818074380148a19");
        CurrentWeather cwd = owm.currentWeatherByCityName(cityName);

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode weather = mapper.createObjectNode();

        celsius = cwd.getMainData().getTemp() - 273.15;

        temperature = round(celsius, 2);

        weather.put("cityName", cwd.getCityName());
        weather.put("windSpeed", cwd.getWindData().getSpeed());
        weather.put("temperature", temperature);
        weather.put("humidity", cwd.getMainData().getHumidity());

        return weather;
    }


    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
