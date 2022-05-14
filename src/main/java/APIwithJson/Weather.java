package APIwithJson;

import jakarta.persistence.*;

import java.util.HashMap;
import java.util.Map;
@Table(name="weather")
@Entity
public class Weather {

    @Column(name = "id")
    private Integer id;
    @Column(name = "main")
    private String main;
    @Column(name = "description")
    private String description;
    @Column(name = "icon")
    private String icon;
    @Id
    @Column(name = "c_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int c_id;
//    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Weather() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
