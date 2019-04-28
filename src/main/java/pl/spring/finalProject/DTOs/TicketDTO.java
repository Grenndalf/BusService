package pl.spring.finalProject.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class TicketDTO {

    @Size (min = 1, max = 6)
    private int seatNumber;
    private TravelerDTO travelerDTO;
}
