package pl.spring.finalProject.domain.entities;

import lombok.*;
import pl.spring.finalProject.DTOs.ReturnTicketsOfChosenTravelerDTO;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NamedNativeQuery ( name = "travelerTicketsFounderQuery", query =
        "select t.id as ticketId, \n" +
                "sp.city as startPointCity, \n" +
                "sp.railway_address as spAddress, \n" +
                "ep.city as endPointCity,\n" +
                "ep.railway_address as epAddress,\n" +
                "b.travel_date as travelDate, \n" +
                "b.departure_time as departureTime, \n" +
                "t.seat_number as seatNumber\n" +
                "FROM ticket t JOIN traveler tr on t.ticket_owner=tr.id\n" +
                "JOIN bus b on t.bus_id=b.id\n" +
                "join railways sp on sp.id=b.start_point_id\n" +
                "join railways ep on ep.id=b.end_point_id " +
                "where tr.login=:login", resultSetMapping = "ticketsMapping" )
@SqlResultSetMapping ( name = "ticketsMapping", classes = @ConstructorResult ( targetClass = ReturnTicketsOfChosenTravelerDTO.class,
        columns = {
                @ColumnResult ( name = "ticketId", type = Long.class ),
                @ColumnResult ( name = "startPointCity", type = String.class ),
                @ColumnResult ( name = "spAddress", type = String.class ),
                @ColumnResult ( name = "endPointCity", type = String.class ),
                @ColumnResult ( name = "epAddress", type = String.class ),
                @ColumnResult ( name = "travelDate", type = LocalDate.class ),
                @ColumnResult ( name = "departureTime", type = LocalTime.class ),
                @ColumnResult ( name = "seatNumber", type = Integer.class ),
        } )
)
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "ticketOwner")

    private Traveler traveler;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Bus bus;

    public Ticket(int seatNumber, Bus bus) {
        this.seatNumber = seatNumber;
        this.bus = bus;
    }
}
