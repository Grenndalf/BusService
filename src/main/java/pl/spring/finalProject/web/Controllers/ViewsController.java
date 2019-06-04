package pl.spring.finalProject.web.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping ( "/AdminPanel" )
    public String AdminTools() {
        return "AdminPanel";
    }

    @RequestMapping ( "/UserPanel" )
    public String UserTools() {
        return "UserPanel";
    }

    @RequestMapping ( "/" )
    public String MainPage() {
        return "MainPage";
    }

    @RequestMapping ( "/default" )
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/AdminPanel";
        } else {
            return "redirect:/searchConnections";
        }
    }
}
