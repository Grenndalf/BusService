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

    private MailSender mailSender;
    private TravelerService travelerService;

    public TravelerController(MailSender mailSender, TravelerService travelerService) {
        this.mailSender = mailSender;
        this.travelerService = travelerService;
    }

    @CrossOrigin
    @RequestMapping (value = "/saveTraveler")
    public ResponseEntity<?> postSample(@Valid @RequestBody TravelerDTO travelerDTO) {

     if (!travelerService.isAvailable(travelerDTO.getLogin())) {
         return ResponseEntity.badRequest().body("This login already exist");
     }
        travelerService.saveNewTraveler(travelerDTO);
        System.out.println(travelerDTO.getEmail());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(travelerDTO.getEmail());
        message.setFrom("b.olczak4@wp.pl");
        message.setSubject("Test wysyłki");
        message.setText("Jakie to wszystko jest proste :) ");

        // Wysyłamy obiekt typu SimpleMimeMessage z użyciem bean'a MailSender
        mailSender.send(message);
        System.out.println("wyslano");
        return ResponseEntity.ok("user Saved");
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
