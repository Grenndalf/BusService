package pl.spring.finalProject.web.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.Services.BusService;
import pl.spring.finalProject.Services.RailwaysService;
import pl.spring.finalProject.domain.entities.Railways;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BusController {

    private BusService busService;
    private RailwaysService railwaysService;

    public BusController(BusService busService, RailwaysService railwaysService) {
        this.busService = busService;
        this.railwaysService = railwaysService;
    }

    @CrossOrigin
    @RequestMapping(name = "/getConnections")
    public List<BusDTO> getConnections(@Valid @RequestBody BusDTO busDTO) {
        Railways start = railwaysService.findRailwaysBasedOnRailwayDTO(busDTO.getStartPoint());
        Railways end = railwaysService.findRailwaysBasedOnRailwayDTO(busDTO.getEndPoint());
        String day = busDTO.getTravelDate().toString();
        return busService.getBusDTOS(start,end,day);

    }
}
