package c4c.kafka.example.weatherconsumer.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.Calendar;

@Entity(name = "log")
public class WeatherLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "incrementDomain")
    @GenericGenerator(name = "incrementDomain", strategy = "increment")
    private int id;

    private String city;
    private BigDecimal latitude;

    private BigDecimal longitude;
    private BigDecimal temp;
    private Calendar logdate;

    public WeatherLog(String city, BigDecimal latitude, BigDecimal longitude, BigDecimal temp, Calendar logdate) {
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.temp = temp;
        this.logdate = logdate;

    }
    public WeatherLog(){}
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getTemp() {
        return temp;
    }

    public void setTemp(BigDecimal temp) {
        this.temp = temp;
    }

    public Calendar getLogdate() {
        return logdate;
    }

    public void setLogdate(Calendar logdate) {
        this.logdate = logdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
