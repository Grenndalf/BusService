package pl.spring.finalProject.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.spring.finalProject.DTOs.ReturnResultFromGetConnetionsMethodDTO;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NamedNativeQuery ( name = "ConnectionFouderQuery", query =
        "SELECT b.id as busId,\n" +
                "rs.city as startPointCity,\n" +
                "rs.railway_address as startPointRailwayAddress,\n" +
                "re.city as endPointCity,\n" +
                "re.railway_address as endpointRailwayAddress,\n" +
                "b.departure_time as departureTime,\n" +
                "b.travel_date as travelDate,\n" +
                "6-count(t.seat_number) as seatsAvailable\n" +
                "FROM bus b\n" +
                "left join ticket t on b.id=t.bus_id\n" +
                "join railways rs on rs.id = b.start_point_id\n" +
                "join railways re on re.id = b.end_point_id\n" +
                "WHERE " +
                "rs.city=:spCity " +
                "and rs.railway_address=:spRailwayAddress " +
                "and re.city=:epCity " +
                "and re.railway_address=:epRailwayAddress " +
                "and b.travel_date=:travelDate " +
                "GROUP BY b.id", resultSetMapping = "MySuperHiperResultSet" )
@SqlResultSetMapping ( name = "MySuperHiperResultSet",
        classes = @ConstructorResult ( targetClass = ReturnResultFromGetConnetionsMethodDTO.class,
                columns = {
                        @ColumnResult ( name = "busId", type = Long.class ),
                        @ColumnResult ( name = "startPointCity", type = String.class ),
                        @ColumnResult ( name = "startPointRailwayAddress", type = String.class ),
                        @ColumnResult ( name = "endPointCity", type = String.class ),
                        @ColumnResult ( name = "endpointRailwayAddress", type = String.class ),
                        @ColumnResult ( name = "departureTime", type = String.class ),
                        @ColumnResult ( name = "travelDate", type = String.class ),
                        @ColumnResult ( name = "seatsAvailable", type = Integer.class ),
                } )
)
public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn
    private Railways startPoint;

    @OneToOne
    @JoinColumn
    private Railways endPoint;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate travelDate;

    @Size(min = 0, max = 6)
    @OneToMany ( cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "bus" )
    private List<Ticket> ticketList;

    @JsonFormat (pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime departureTime;

}
