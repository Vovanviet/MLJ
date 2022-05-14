package APIwithJson;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
@Table(name = "wind")
@Entity
public class Wind {


    @Column(name = "speed")
    private Double speed;
    @Column(name = "deg")
    private Integer deg;
    @Column(name = "gust")
    private Double gust;
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Wind() {
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Integer getDeg() {
        return deg;
    }

    public void setDeg(Integer deg) {
        this.deg = deg;
    }

    public Double getGust() {
        return gust;
    }

    public void setGust(Double gust) {
        this.gust = gust;
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
                "speed=" + speed +
                ", deg=" + deg +
                ", gust=" + gust +
                '}';
    }
}
