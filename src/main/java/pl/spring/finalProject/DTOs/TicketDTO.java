package pl.spring.finalProject.DTOs;

import pl.spring.finalProject.domain.entities.Railways;
import pl.spring.finalProject.domain.entities.Traveler;

import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

public class TicketDTO {
    @Size (min = 1, max = 6)
    private int seatNumber;
    @OneToOne
    @JoinColumn (name = "departureFrom")
    private Railways departurePlace;
    @OneToOne
    @JoinColumn(name = "travelDestination")
    private Railways destinationPlace;
    @Future
    private LocalDate travelDate;

    private LocalTime travelTime;
    private TravelerDTO travelerDTO;
}
