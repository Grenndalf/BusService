package pl.spring.finalProject.Services;

import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.DTOs.RailwaysDTO;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.domain.entities.Bus;
import pl.spring.finalProject.domain.entities.Railways;
import pl.spring.finalProject.domain.entities.Traveler;

public class Converters {

//    public static Railways convertRailwayDTO(RailwaysDTO railwaysDTO) {
//        Railways railways = new Railways();
//        railways.setCity(railwaysDTO.getCity());
//        railways.setRailwayAddress(railwaysDTO.getRailwayAddress());
//        return railways;
//    }

    public static RailwaysDTO convertRailways(Railways railways) {
        RailwaysDTO railwaysDTO = new RailwaysDTO();
        railwaysDTO.setCity(railways.getCity());
        railwaysDTO.setRailwayAddress(railways.getRailwayAddress());
        return railwaysDTO;
    }

    public static BusDTO convertBuses(Bus bus) {
        BusDTO busDTO = new BusDTO();
        busDTO.setStartPoint(convertRailways(bus.getStartPoint()));
        busDTO.setEndPoint(convertRailways(bus.getEndPoint()));
        busDTO.setTravelDate(bus.getTravelDate());
        busDTO.setDepartureTime(bus.getDepartureTime());
        return busDTO;
    }

    public static Traveler convertTravelerDTO(TravelerDTO travelerDTO) {
        Traveler traveler = new Traveler();
        traveler.setLogin(travelerDTO.getLogin());
        traveler.setpassword(travelerDTO.getPassword());
        traveler.setfirstName(travelerDTO.getFirstName());
        traveler.setlastName(travelerDTO.getLastName());
        return traveler;
    }

}
