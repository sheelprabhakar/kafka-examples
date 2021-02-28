package c4c.kafka.example.weatheragent.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;

public class WeatherInfo implements Serializable {

    private String city;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private Calendar logDate;
    private float temp;

    public WeatherInfo(String city, BigDecimal latitude, BigDecimal longitude, float temp) {
        this.city = city;
        this.longitude = longitude;
        this.latitude = latitude;
        this.temp = temp;
        this.logDate = Calendar.getInstance();

    }
    public WeatherInfo(){

    }
    public Calendar getLogDate() {
        return logDate;
    }

    public void setLogDate(Calendar logDate) {
        this.logDate = logDate;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }
}
