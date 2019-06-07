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
        return "connections";
    }

    @RequestMapping ( "/yourTicket" )
    public String selectedConnection() {
        return "ticketTransaction";
    }

    @RequestMapping ( "/AdminPanel" )
    public String AdminTools() {
        return "adminPanel";
    }

    @RequestMapping ( "/UserPanel" )
    public String UserTools() {
        return "userPanel";
    }

    @RequestMapping ( "/" )
    public String MainPage() {
        return "mainPage";
    }

    @RequestMapping ( "/login" )
    public String LoginPage() {
        return "loginPage";
    }

    @RequestMapping ( "/logError" )
    public String LoginErrorPage() {
        return "loginErrorPage";
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
