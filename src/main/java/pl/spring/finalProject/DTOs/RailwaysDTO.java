package pl.spring.finalProject.DTOs;

public class RailwaysDTO {

    private String City;
    private String RailwayAddress;

    public RailwaysDTO() {
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
