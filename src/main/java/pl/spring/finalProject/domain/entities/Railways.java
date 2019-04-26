package pl.spring.finalProject.domain.entities;

import javax.persistence.*;

@Entity
@Table ( name = "Railways" )
public class Railways {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private long id;
    private String city;
    private String railwayAddress;

    public Railways() {
    }

    @Override
    public String toString() {
        return "Railways{" +
                "id=" + id +
                ", City='" + city + '\'' +
                ", RailwayAddress='" + railwayAddress + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRailwayAddress() {
        return railwayAddress;
    }

    public void setRailwayAddress(String railwayAddress) {
        this.railwayAddress = railwayAddress;
    }
}
