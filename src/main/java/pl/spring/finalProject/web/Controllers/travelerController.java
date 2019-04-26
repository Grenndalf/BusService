package pl.spring.finalProject.web.Controllers;

import org.springframework.web.bind.annotation.*;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.Services.TravelerService;

import javax.validation.Valid;
@RestController
public class travelerController {

    private TravelerService travelerService;

    public travelerController(TravelerService travelerService) {
        this.travelerService = travelerService;
    }

    @CrossOrigin
    @RequestMapping (value = "/saveTraveler", method = RequestMethod.POST)
    public void postSample(@Valid @RequestBody TravelerDTO travelerDTO) {


        System.out.println(travelerDTO);
        travelerService.saveNewTraveler(travelerDTO);
        System.out.println("job Done");

    }
}
