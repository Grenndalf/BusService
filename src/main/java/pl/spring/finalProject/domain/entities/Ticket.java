package pl.spring.finalProject.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Size(min = 1, max = 6)
    private int seatNumber;
    @OneToOne
    @JoinColumn(name = "departureFrom")
    private Railways departurePlace;
    @OneToOne
    @JoinColumn(name = "travelDestination")
    private Railways destinationPlace;
    @Future
    private LocalDate travelDate;
    @Future
    private LocalTime travelTime;
    @ManyToOne
    @JoinColumn(name = "ticketOwner")
    private Traveler traveler;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", seatNumber=" + seatNumber +
                ", departurePlace=" + departurePlace +
                ", destinationPlace=" + destinationPlace +
                ", travelDate=" + travelDate +
                ", travelTime=" + travelTime +
                '}';
    }
    public Ticket() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Railways getDeparturePlace() {
        return departurePlace;
    }

    public void setDeparturePlace(Railways departurePlace) {
        this.departurePlace = departurePlace;
    }

    public Railways getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(Railways destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }

    public LocalTime getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(LocalTime travelTime) {
        this.travelTime = travelTime;
    }
}
