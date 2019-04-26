package pl.spring.finalProject.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class RailwaysDTO {
    @NotNull
    @NotBlank
    private String City;
    @NotNull
    @NotBlank
    private String RailwayAddress;

    public RailwaysDTO() {
    }

    @Override
    public String toString() {
        return "RailwaysDTO{" +
                "City='" + City + '\'' +
                ", RailwayAddress='" + RailwayAddress + '\'' +
                '}';
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getRailwayAddress() {
        return RailwayAddress;
    }

    public void setRailwayAddress(String railwayAddress) {
        RailwayAddress = railwayAddress;
    }
}
