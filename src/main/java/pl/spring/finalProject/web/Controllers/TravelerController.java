package pl.spring.finalProject.web.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.Services.TravelerService;
import pl.spring.finalProject.Services.validationGroup;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;

@RestController
public class TravelerController {

    private TravelerService travelerService;

    public TravelerController(TravelerService travelerService) {
        this.travelerService = travelerService;
    }

    @CrossOrigin
    @RequestMapping (value = "/saveTraveler")
    public ResponseEntity<?> saveUser(@Valid @RequestBody TravelerDTO travelerDTO) {
        if (travelerService.isAvailable(travelerDTO.getLogin())){
        travelerService.saveNewTraveler(travelerDTO);
        return ResponseEntity.ok("user Saved");
    }else
        return ResponseEntity.badRequest().body("this login is already in use");
    }

    @RequestMapping ( "/getUser" )
    @ResponseBody
    public HashMap getUser(Principal principal) {
        String login = principal.getName();
        return travelerService.getFirstAndLastName(login);
    }

    @RequestMapping ("/updateUser")
    public ResponseEntity<String> updateTraveler(@Validated({validationGroup.class})@RequestBody TravelerDTO travelerDTO,Principal principal){
        System.out.println(travelerDTO.toString());

        travelerService.updateUserData(travelerDTO.getFirstName(),travelerDTO.getLastName(),travelerDTO.getEmail(),principal.getName());
        return ResponseEntity.ok("cokolwiek");
    }


    @RequestMapping ("/userData")
    @ResponseBody
    public HashMap getUserData(Principal principal){
        return travelerService.getFirstAndLastnameAndEmailByLogin(principal.getName());
    }

}
