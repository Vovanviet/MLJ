package APIwithJson;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
@Table(name = "coord")
@Entity
public class Coord {
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;


    @Column(name = "lon")
    private Double lon;
    @Column(name = "lat")
    private Double lat;

    public Coord() {
    }
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
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
                "lon=" + lon +
                ", lat=" + lat +
                '}';
    }
}
