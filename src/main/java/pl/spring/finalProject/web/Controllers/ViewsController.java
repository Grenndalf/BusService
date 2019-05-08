package pl.spring.finalProject.web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewsController {

    @RequestMapping("/RegisterForm")
    public String registry() {
        return "register";
    }

    @RequestMapping("/searchConnections")
    public String connections() {
        return "DropDownList";
    }

    @RequestMapping ( "/yourTicket" )
    public String selectedConnection() {
        return "ticketTransaction";
    }

}
