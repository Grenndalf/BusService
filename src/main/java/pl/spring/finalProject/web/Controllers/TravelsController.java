package pl.spring.finalProject.web.Controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.Repositories.BusRepository;
import pl.spring.finalProject.Repositories.RailwaysRepository;
import pl.spring.finalProject.domain.entities.Railways;


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

        System.out.println("**********************************");
        Railways sp = railwaysRepository.findByCityAndRailwayAddress(busDTO.getStartPoint().getCity(),busDTO.getStartPoint().getRailwayAddress());
        Railways ep = railwaysRepository.findByCityAndRailwayAddress(busDTO.getEndPoint().getCity(),busDTO.getEndPoint().getRailwayAddress());
        System.out.println(sp.getId());
        System.out.println(ep.getId());
        System.out.println(busDTO.getTravelDate().toString());
        System.out.println("**********************************");
        System.out.println(busRepository.findbysomespid(sp.getId()));
        System.out.println("**********************************");
        System.out.println(busRepository.findbysomeepid(ep.getId()));
        System.out.println("**********************************");
        System.out.println(busRepository.findCustomQuery2(sp.getId(),ep.getId(),busDTO.getTravelDate().toString()));
        System.out.println("**********************************");

        return busDTO.toString();
    }

//    @CrossOrigin
//    @PostMapping( "/busQuery")
//    public List<Bus> JsonInfo(@RequestBody Bus bus){
//
//
//    }
}
