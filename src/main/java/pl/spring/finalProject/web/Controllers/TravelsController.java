package pl.spring.finalProject.web.Controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.Repositories.BusRepository;
import pl.spring.finalProject.Repositories.RailwaysRepository;


@RestController
public class TravelsController {

    private RailwaysRepository railwaysRepository;
    private BusRepository busRepository;

    public TravelsController(RailwaysRepository railwaysRepository,
                             BusRepository busRepository) {
        this.railwaysRepository = railwaysRepository;
        this.busRepository = busRepository;
    }

    @CrossOrigin
    @PostMapping("/postForTravels")
    public String getObjectTest(@RequestBody BusDTO busDTO) {


        return busDTO.toString();
    }

//    @CrossOrigin
//    @PostMapping( "/busQuery")
//    public List<Bus> JsonInfo(@RequestBody Bus bus){
//
//
//    }
}
