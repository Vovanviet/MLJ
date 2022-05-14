package APIwithJson;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
@Table(name = "clouds")
@Entity
public class Clouds {

    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;
    @Column(name = "c_all")
    private Integer all;
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();


    public Clouds() {
    }

    public Integer getAll() {
        return all;
    }

    public void setAll(Integer all) {
        this.all = all;
    }

    public int getId() {
        return c_id;
    }

    public void setId(int id) {
        this.c_id = id;
    }
//    public Map<String, Object> getAdditionalProperties() {
//        return additionalProperties;
//    }

//    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
//        this.additionalProperties = additionalProperties;
//    }

    @Override
    public String toString() {
        return "{" +
                "all=" + all +
                '}';
    }
}
