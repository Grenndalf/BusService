package pl.spring.finalProject.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class TicketDTO {

    private int seatNumber;
    private TravelerDTO travelerDTO;
    private BusDTO busDTO;
}
