package pl.spring.finalProject.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.spring.finalProject.domain.entities.Bus;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class TicketDTO {

    private int seatNumber;
    private TravelerDTO travelerDTO;
    private Bus bus;

    public TicketDTO(int seatNumber, Bus bus) {
        this.seatNumber = seatNumber;
        this.bus = bus;
    }
}
