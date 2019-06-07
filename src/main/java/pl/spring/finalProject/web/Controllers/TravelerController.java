package pl.spring.finalProject.web.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.Services.TravelerService;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.security.Principal;
import java.util.HashMap;

@RestController
public class TravelerController {

    private TravelerService travelerService;

    public TravelerController(TravelerService travelerService) {
        this.travelerService = travelerService;
    }

    @RequestMapping (value = "/saveTraveler")
    public ResponseEntity<?> saveUser(@Valid @RequestBody TravelerDTO travelerDTO) {
        if (travelerService.isAvailable(travelerDTO.getLogin())){
        travelerService.saveNewTraveler(travelerDTO);
        return ResponseEntity.ok("user Saved");
    }else
        return ResponseEntity.badRequest().body("this login is already in use");
    }

    @RequestMapping ( "/getUser" )
    public HashMap getUser(Principal principal) {
        String login = principal.getName();
        return travelerService.getFirstAndLastName(login);
    }

    @RequestMapping ("/userData")
    public HashMap getUserData(Principal principal){
        return travelerService.getfirstandlastnameandemailbylogin(principal.getName());
    }

    @RequestMapping ( "/changeUserFirstName" )
    public HashMap changeUserFirstName(Principal principal) {
        return travelerService.getfirstandlastnameandemailbylogin(principal.getName());
    }

    @RequestMapping ( "/getUserLogin" )
    public ResponseEntity<String> userName(Principal principal) {
        try {
            return ResponseEntity.ok(principal.getName());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("no logged user");
        }
    }

    @RequestMapping ( "/updateUserFirstName" )
    public ResponseEntity<String> updateUserFirstName(@RequestBody String firstName, Principal principal) {
        firstName = firstName.replace("\"", "");
        if (firstName.matches("[A-Za-z]{3,10}$")) {
            travelerService.updateUserFirstName(firstName, principal.getName());
            return ResponseEntity.ok("User Updated");
        } else {
            return ResponseEntity.badRequest().body("User NOT Updated");
        }
    }


    @RequestMapping ( "/updateUserLastName" )
    public ResponseEntity<String> updateUserLastName(@RequestBody String lastName, Principal principal) {
        lastName = lastName.replace("\"", "");
        if (lastName.matches("[A-Za-z]{3,10}$")) {
            travelerService.updateUserLastName(lastName, principal.getName());
            return ResponseEntity.ok("User Updated");
        } else {
            return ResponseEntity.badRequest().body("User NOT Updated");
        }
    }

    @RequestMapping ( "/updateUserEmailAddress" )
    @ResponseStatus ( value = HttpStatus.OK )
    public void updateUserEmailAddress(@Email @RequestBody String email, Principal principal) {
        travelerService.updateUserFirstName(email, principal.getName());
    }
}
