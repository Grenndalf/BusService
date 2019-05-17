package pl.spring.finalProject.web.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.spring.finalProject.DTOs.SomeSimpleClassForLogIn;
import pl.spring.finalProject.Repositories.TravelerRepository;
import pl.spring.finalProject.Services.BusService;
import pl.spring.finalProject.domain.entities.Traveler;

import java.security.Principal;

@Controller
public class TestController {

    private BusService busService;
    private PasswordEncoder passwordEncoder;
    private TravelerRepository travelerRepository;

    public TestController(BusService busService, PasswordEncoder passwordEncoder, TravelerRepository travelerRepository) {
        this.busService = busService;
        this.passwordEncoder = passwordEncoder;
        this.travelerRepository = travelerRepository;
    }

    @RequestMapping ( "/getUserLogin" )
    @ResponseBody
    public ResponseEntity<String> test(Principal principal) {
        try {
            return ResponseEntity.ok(principal.getName());
        } catch (NullPointerException e) {
            return ResponseEntity.badRequest().body("no logged user");
        }
    }

    @RequestMapping ( "/hmm" )
    @ResponseBody
    public ResponseEntity<String> test(@RequestBody SomeSimpleClassForLogIn someSimpleClassForLogIn) {
        System.out.println(someSimpleClassForLogIn.toString());
        System.out.println(someSimpleClassForLogIn.getLogin());
        System.out.println(someSimpleClassForLogIn.getPassword());
        System.out.println("************************************");
        Traveler traveler = travelerRepository.findByLogin(someSimpleClassForLogIn.getLogin());
        System.out.println(traveler);
        System.out.println("************************************");
        System.out.println(passwordEncoder.matches(someSimpleClassForLogIn.getPassword(),traveler.getPassword()));



        if (someSimpleClassForLogIn.getLogin().length() > 7 || passwordEncoder.matches(someSimpleClassForLogIn.getPassword(),traveler.getPassword())) {
            return ResponseEntity.ok("yeah");
        } else {
            return ResponseEntity.badRequest().body("nope");
        }
    }


}
