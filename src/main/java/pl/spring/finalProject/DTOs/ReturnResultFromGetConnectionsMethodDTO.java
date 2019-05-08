package pl.spring.finalProject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnResultFromGetConnectionsMethodDTO {

    private long busId;
    private String startPointCity;
    private String startPointRailwayAddress;
    private String endPointCity;
    private String endpointRailwayAddress;
    private LocalTime departureTime;
    private LocalDate travelDate;
    private int seatsAvailable;
}
