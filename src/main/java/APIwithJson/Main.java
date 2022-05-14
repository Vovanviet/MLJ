package APIwithJson;

import jakarta.persistence.*;

@Table(name = "main")
@Entity
public class Main {

    @Column(name = "temp")
    private Double temp;
    @Column(name = "feels_like")
    private double feels_like;
    @Column(name = "temp_min")
    private double temp_min;
    @Column(name = "temp_max")
    private double temp_max;
    @Column(name = "pressure")
    private Integer pressure;
    @Column(name = "humidity")
    private Integer humidity;
    @Column(name = "sea_level")
    private Integer sea_level;
    @Column(name = "grnd_level")
    private Integer grnd_level;
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Main() {
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public double getFeelsLike() {
        return feels_like;
    }

    public void setFeelsLike(Double feelsLike) {
        this.feels_like = feelsLike;
    }

    public double getTempMin() {
        return temp_min;
    }

    public void setTempMin(Double tempMin) {
        this.temp_min = tempMin;
    }

    public double getTempMax() {
        return temp_max;
    }

    public void setTempMax(Double tempMax) {
        this.temp_max = tempMax;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public Integer getSeaLevel() {
        return sea_level;
    }

    public void setSeaLevel(Integer seaLevel) {
        this.sea_level = seaLevel;
    }

    public Integer getGrndLevel() {
        return grnd_level;
    }

    public void setGrndLevel(Integer grndLevel) {
        this.grnd_level = grndLevel;
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
                "temp=" + temp +
                ", feels_like=" + feels_like +
                ", temp_min=" + temp_min +
                ", temp_max=" + temp_max +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", sea_level=" + sea_level +
                ", grnd_level=" + grnd_level +
                '}';
    }
}
