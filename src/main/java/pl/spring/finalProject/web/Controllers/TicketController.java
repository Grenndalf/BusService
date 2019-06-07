package pl.spring.finalProject.web.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;
import pl.spring.finalProject.DTOs.ReturnTicketsOfChosenTravelerDTO;
import pl.spring.finalProject.Services.TicketService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @RequestMapping ( "/buyTicket/{busId}" )
    public String buyTicket(@PathVariable ( "busId" ) long id, HttpServletResponse response) {
       ticketService.cookieBusIdCreated(id, response);
        return "redirect:/yourTicket";
    }

    @RequestMapping ( "/seats" )
    @ResponseBody
    public ArrayList<Integer> seatsAvailability(HttpServletRequest request) {
        Cookie id = WebUtils.getCookie(request, "ticketChoice");
        assert id != null;
        return ticketService.seats(Long.valueOf(id.getValue()));
    }

    @RequestMapping("/TicketPurchase")
    public ResponseEntity<String> purchase(@NotNull @RequestBody Integer seat, HttpServletRequest request, Principal principal){
        ticketService.saveTicket(request,principal,seat);
        return ResponseEntity.ok("Ok");
    }

    @RequestMapping ( "/UserTravels" )
    @ResponseBody
    public List<ReturnTicketsOfChosenTravelerDTO> userReservations(@NotNull Principal principal) {
        return ticketService.findTicketsOfChosenTraveler(principal.getName());
    }

    @RequestMapping ( "/deleteReservation" )
    public ResponseEntity<String> removeUserFromTicket(@RequestBody int ticket, Principal principal) {
        ticketService.removeUserFromTicket(ticket,principal.getName());
        return ResponseEntity.ok("OK");
    }
}
