package pl.spring.finalProject.web.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.spring.finalProject.Services.BusService;

import java.security.Principal;

@Controller
public class TestController {

    private BusService busService;

    public TestController(BusService busService) {
        this.busService = busService;
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
}
