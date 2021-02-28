package c4c.kafka.example.weatheragent.dto;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity(name = "cities")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Column(name = "state_id")
    private int stateId;

    @Column(name = "country_id")
    private int countryId;

    @Column(name = "country_code")
    private String countryCode;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @Column(name = "created_at")
    private Calendar createdAt;

    @Column(name = "updated_at")
    private Calendar updateAt;

    @Column(name = "flag")
    private boolean flag;

    @Column(name = "wiki_data_id")
    private String wikiDataId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStateId() {
        return stateId;
    }

    public void setStateId(int stateId) {
        this.stateId = stateId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public Calendar getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Calendar updateAt) {
        this.updateAt = updateAt;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getWikiDataId() {
        return wikiDataId;
    }

    public void setWikiDataId(String wikiDataId) {
        this.wikiDataId = wikiDataId;
    }
}
