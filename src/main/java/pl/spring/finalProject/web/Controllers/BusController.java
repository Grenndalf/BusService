package pl.spring.finalProject.web.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.spring.finalProject.DTOs.BusDTO;

@RestController
public class BusController {


    @CrossOrigin
    @RequestMapping(name = "/getConnections")
    public void getConnections(BusDTO busDTO){



    }
}
