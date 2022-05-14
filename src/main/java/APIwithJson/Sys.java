package APIwithJson;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
@Table(name = "sys")
@Entity
public class Sys {
    @Column(name = "type")
    private Integer type;

    @Column(name = "id")
    private Integer id;
    @Column(name = "country")
    private String country;
    @Column(name = "sunrise")
    private Integer sunrise;
    @Column(name = "sunset")
    private Integer sunset;
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int c_id;

    public Sys() {
    }
    //    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Integer getSunrise() {
        return sunrise;
    }

    public void setSunrise(Integer sunrise) {
        this.sunrise = sunrise;
    }

    public Integer getSunset() {
        return sunset;
    }

    public void setSunset(Integer sunset) {
        this.sunset = sunset;
    }

//    public Map<String, Object> getAdditionalProperties() {
//        return additionalProperties;
//    }
//
//    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
//        this.additionalProperties = additionalProperties;
//    }

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", id=" + id +
                ", country='" + country + '\'' +
                ", sunrise=" + sunrise +
                ", sunset=" + sunset +
                '}';
    }
}
