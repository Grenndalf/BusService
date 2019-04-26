package pl.spring.finalProject.web.Controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.Repositories.TravelerRepository;
import pl.spring.finalProject.Services.TravelerService;

import javax.validation.Valid;


@RestController
public class SomeSimpleController {


    private TravelerRepository travelerRepository;
    private TravelerService travelerService;
    private PasswordEncoder passwordEncoder;


    public SomeSimpleController(TravelerRepository travelerRepository,
                                TravelerService travelerService,
                                PasswordEncoder passwordEncoder) {
        this.travelerRepository = travelerRepository;
        this.travelerService = travelerService;
        this.passwordEncoder = passwordEncoder;
    }

    @CrossOrigin
    @RequestMapping(value = "/saveMe", method = RequestMethod.POST)
    public void postSample(@Valid @RequestBody TravelerDTO travelerDTO) {


        System.out.println(travelerDTO);
        travelerService.saveNewTraveler(travelerDTO);
        System.out.println("job Done");

    }
}
