package pl.spring.finalProject.domain.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.spring.finalProject.DTOs.ReturnResultFromGetConnectionsMethodDTO;

import javax.persistence.*;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table (uniqueConstraints={@UniqueConstraint(columnNames = {"end_point_id","start_point_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@NamedNativeQuery ( name = "ConnectionFounderQuery", query =
        "SELECT b.id as busId,\n" +
                "rs.city as startPointCity,\n" +
                "rs.railway_address as startPointRailwayAddress,\n" +
                "re.city as endPointCity,\n" +
                "re.railway_address as endpointRailwayAddress,\n" +
                "b.departure_time as departureTime,\n" +
                "b.travel_date as travelDate,\n" +
                "count(t.seat_number) as seatsAvailable\n" +
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
                "and t.ticket_owner IS NULL " +
                "GROUP BY b.id,rs.city, rs.railway_address, re.city, re.railway_address, b.departure_time, b.travel_date", resultSetMapping = "MySuperHiperResultSet" )
@SqlResultSetMapping ( name = "MySuperHiperResultSet",
        classes = @ConstructorResult ( targetClass = ReturnResultFromGetConnectionsMethodDTO.class,
                columns = {
                        @ColumnResult ( name = "busId", type = Long.class ),
                        @ColumnResult ( name = "startPointCity", type = String.class ),
                        @ColumnResult ( name = "startPointRailwayAddress", type = String.class ),
                        @ColumnResult ( name = "endPointCity", type = String.class ),
                        @ColumnResult ( name = "endpointRailwayAddress", type = String.class ),
                        @ColumnResult ( name = "departureTime", type = LocalTime.class ),
                        @ColumnResult ( name = "travelDate", type = LocalDate.class ),
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

    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate travelDate;

    @FutureOrPresent
    @JsonFormat (pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime departureTime;

    @Max ( 100 )
    @Min ( 1 )
    private int maxNumberOfSeatsAvailable;

}
