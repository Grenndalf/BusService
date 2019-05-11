package pl.spring.finalProject.DTOs;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Convert;
import javax.validation.constraints.*;
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

    @JsonFormat ( pattern = "HH:mm" )
    @DateTimeFormat ( pattern = "HH:mm" )
    private LocalTime departureTime;

    @FutureOrPresent
    @NotNull
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat (pattern = "dd/MM/yyyy")
    @Convert ( converter = Jsr310JpaConverters.LocalDateConverter.class )
    private LocalDate travelDate;

    @Max ( 100 )
    @Min ( 1 )
    private Integer maxNumberOfSeatsAvailable;
}
