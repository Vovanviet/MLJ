package APIwithJson;

import jakarta.persistence.*;

import java.time.LocalDate;

@Table(name = "weatherroot")
@Entity
public class WeatherRoot2 {
    @Column(name = "base")
    private String base;
    @Column(name = "visiblity")
    private Integer visiblity;
    @Column(name = "dt")
    private Integer dt;
    @Column(name = "timezone")
    private Integer timezone;
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "cod")
    private Integer cod;
    @Column(name = "dateTime")
    private LocalDate dateTime;
    @Column(name = "c_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int c_id;

    public WeatherRoot2() {
    }

    public WeatherRoot2(String base, Integer visiblity , Integer dt, Integer timezone  , Integer id, String name, Integer cod, LocalDate dateTime) {
        this.base = base;
        this.visiblity = visiblity;
        this.dt = dt;
        this.timezone = timezone;
        this.id = id;
        this.name = name;
        this.cod = cod;
        this.dateTime= dateTime;

    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public Integer getVisiblity() {
        return visiblity;
    }

    public void setVisiblity(Integer visiblity) {
        this.visiblity = visiblity;
    }

    public Integer getDt() {
        return dt;
    }

    public void setDt(Integer dt) {
        this.dt = dt;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "{" +
                "base='" + base + '\'' +
                ", visiblity=" + visiblity +
                ", dt=" + dt +
                ", timezone=" + timezone +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", cod=" + cod +
                ", dateTime=" + dateTime +
                '}';
    }
}
