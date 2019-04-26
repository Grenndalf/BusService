package pl.spring.finalProject.web.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.spring.finalProject.DTOs.RailwaysDTO;
import pl.spring.finalProject.Services.RailwaysService;

import java.util.List;

@RestController
public class RailwaysController {

    private RailwaysService railwaysService;

    public RailwaysController(RailwaysService railwaysService) {
        this.railwaysService = railwaysService;
    }

    @CrossOrigin
    @GetMapping("/getRailways")
    public List<RailwaysDTO> RailwaysList() {
        List<RailwaysDTO> list = railwaysService.railwaysListConverter();
        return list;
    }

}
