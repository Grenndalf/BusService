package pl.spring.finalProject.Services;

import pl.spring.finalProject.DTOs.RailwaysDTO;
import pl.spring.finalProject.domain.entities.Railways;

import java.util.List;

public interface RailwaysService {

    List<RailwaysDTO> railwaysListConverter();
    Railways findRailwaysBasedOnRailwayDTO(RailwaysDTO railwaysDTO);

}
