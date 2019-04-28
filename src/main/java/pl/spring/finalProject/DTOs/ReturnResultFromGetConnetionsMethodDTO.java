package pl.spring.finalProject.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnResultFromGetConnetionsMethodDTO {

    private long busId;
    private String startPointCity;
    private String startPointRailwayAddress;
    private String endPointCity;
    private String endpointRailwayAddress;
    private String departureTime;
    private String travelDate;
    private int seatsAvailable;
}
