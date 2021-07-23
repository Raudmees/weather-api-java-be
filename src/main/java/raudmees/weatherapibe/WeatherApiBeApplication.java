package raudmees.weatherapibe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WeatherApiBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApiBeApplication.class, args);
    }

}
