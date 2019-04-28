package pl.spring.finalProject.web.Controllers;

import org.springframework.http.ResponseEntity;
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
    @RequestMapping (value = "/saveTraveler")
    public ResponseEntity<?> postSample(@Valid @RequestBody TravelerDTO travelerDTO) {

     if (!travelerService.isAvailable(travelerDTO.getLogin())) {
         return ResponseEntity.badRequest().body("This login already exist");
     }
    travelerService.saveNewTraveler(travelerDTO);

        return ResponseEntity.ok(travelerDTO);
    }


}
