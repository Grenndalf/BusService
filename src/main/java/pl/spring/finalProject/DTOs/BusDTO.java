package pl.spring.finalProject.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class BusDTO {

    @NotNull
    private RailwaysDTO startPoint;
    @NotNull
    private RailwaysDTO endPoint;
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat (pattern = "dd/MM/yyyy")
    private LocalDate travelDate;
    @Future
    @JsonFormat (pattern = "HH:mm")
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime departureTime;

}
