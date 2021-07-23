package raudmees.weatherapibe.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity(name = "record_points")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class RecordPoint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long record_point_id;

    private Double temperature;
    private Double windSpeed;
    private Double humidity;
    private String cityName;

    @CreationTimestamp
    private Timestamp time;

    @ManyToOne
    @JoinColumn(name="city_fk")
    private City city;

}
