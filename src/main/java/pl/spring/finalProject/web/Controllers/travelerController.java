package pl.spring.finalProject.web.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.Services.TravelerService;
import pl.spring.finalProject.Services.validationGroup;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;

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
        return ResponseEntity.ok("user Saved");
    }

    @RequestMapping ( "/getUser" )
    @ResponseBody
    public HashMap getUser(Principal principal) {
        String login = principal.getName();
        return travelerService.getFirstAndLastName(login);
    }

    @RequestMapping ("/updateUser")
    public ResponseEntity<String> updateTraveler(@Validated({validationGroup.class})@RequestBody TravelerDTO travelerDTO){
        //TODO user update
        return ResponseEntity.ok(travelerDTO.toString());
    }

}
