package pl.spring.finalProject.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn
    private Railways startPoint;

    @OneToOne
    @JoinColumn
    private Railways endPoint;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate travelDate;

    @Size(min = 0, max = 6)
    @ManyToMany
    @JoinTable(name = "Bus_Traveler", joinColumns = @JoinColumn(name = "Bus_Id"),
            inverseJoinColumns = @JoinColumn(name = "Traveler_Id"))
    private List<Traveler> travelerList;

    @JsonFormat (pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                ", date=" + travelDate +
                '}';
    }

    public Bus() {
    }

    public long getId() {
        return id;
    }

    public Railways getEndPoint() {
        return endPoint;
    }

    public Railways getStartPoint() {
        return startPoint;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public List<Traveler> getTravelerList() {
        return travelerList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setEndPoint(Railways endPoint) {
        this.endPoint = endPoint;
    }

    public void setStartPoint(Railways startPoint) {
        this.startPoint = startPoint;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public void setTravelerList(List<Traveler> travelerList) {
        this.travelerList = travelerList;
    }

    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }
}
