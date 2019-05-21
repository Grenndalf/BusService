package pl.spring.finalProject.web.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.spring.finalProject.Repositories.TravelerRepository;
import pl.spring.finalProject.Services.BusService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public ResponseEntity<String> test(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.authenticate(response);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }


        return ResponseEntity.ok("yeah");
    }
}
