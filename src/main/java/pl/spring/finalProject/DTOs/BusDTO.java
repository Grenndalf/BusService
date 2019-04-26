package pl.spring.finalProject.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

public class BusDTO {

    @NotNull
    private RailwaysDTO startPoint;
    @NotNull
    private RailwaysDTO endPoint;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate travelDate;

    @JsonFormat (pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    public BusDTO() {
    }

    public RailwaysDTO getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(RailwaysDTO startPoint) {
        this.startPoint = startPoint;
    }

    public RailwaysDTO getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(RailwaysDTO endPoint) {
        this.endPoint = endPoint;
    }

    public LocalDate getTravelDate() {
        return travelDate;
    }

    public void setTravelDate(LocalDate travelDate) {
        this.travelDate = travelDate;
    }


    public LocalTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

}
