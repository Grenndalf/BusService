package pl.spring.finalProject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnTicketsOfChosenTravelerDTO {

    private long ticketId;
    private String startPointCity;
    private String spAddress;
    private String endPointCity;
    private String epAddress;
    private LocalDate travelDate;
    private LocalTime departureTime;
    private int seatNumber;

}
