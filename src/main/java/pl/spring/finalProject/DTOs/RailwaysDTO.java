package pl.spring.finalProject.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RailwaysDTO {

    @NotNull
    @NotBlank
    private String City;

    @NotNull
    @NotBlank
    private String RailwayAddress;

}
