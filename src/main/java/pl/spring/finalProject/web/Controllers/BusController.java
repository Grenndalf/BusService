package pl.spring.finalProject.web.Controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.DTOs.ReturnResultFromGetConnetionsMethodDTO;
import pl.spring.finalProject.Services.BusService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class BusController {

    private BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @CrossOrigin
    @RequestMapping("/getConnections")
    public List<ReturnResultFromGetConnetionsMethodDTO> getConnections(@Valid @RequestBody BusDTO busDTO, Principal principal) {
        System.out.println(busService.findfullinfo(busDTO));
        return busService.findfullinfo(busDTO);
    }
}
