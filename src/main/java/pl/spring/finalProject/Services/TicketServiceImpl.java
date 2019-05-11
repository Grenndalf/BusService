package pl.spring.finalProject.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;
import pl.spring.finalProject.DTOs.ReturnTicketsOfChosenTravelerDTO;
import pl.spring.finalProject.Repositories.TicketRepository;
import pl.spring.finalProject.domain.entities.Bus;
import pl.spring.finalProject.domain.entities.Traveler;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private TicketRepository ticketRepository;
    private TravelerService travelerService;
    private BusService busService;

    public TicketServiceImpl(TicketRepository ticketRepository, TravelerService travelerService, BusService busService) {
        this.ticketRepository = ticketRepository;
        this.travelerService = travelerService;
        this.busService = busService;
    }

    @Override
    public ArrayList<Integer> seats(long id) {
        return ticketRepository.seats(id);
    }

    @Transactional
    @Override
    public ResponseEntity<String> saveTicket(HttpServletRequest request, @NotNull Principal principal, @NotNull Integer seatNumber) {
        String login = principal.getName();
        long id = getIdFromCookie(request);
        if (id != 0 && login != null) {
            Traveler traveler = travelerService.findbyLogin(login);
            Bus bus = busService.findBusById(id);
            if (ticketRepository.isSeatAvailable(seatNumber, bus)) {
                ticketRepository.ticketUpdate(traveler, bus, seatNumber);
                return ResponseEntity.ok("Reservation done");
            } else {
                return ResponseEntity.badRequest().body("something went wrong");
            }
        } else {
            return ResponseEntity.badRequest().body("something went wrong");
        }
    }

    private long getIdFromCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, "ticketChoice");
        if (cookie != null) {
            return Long.valueOf(cookie.getValue());
        } else {
            return 0L;
        }
    }

    @Override
    public void cookieBusIdCreated(long id, HttpServletResponse response) {
        Cookie ticketChoice = new Cookie("ticketChoice", String.valueOf(id));
        ticketChoice.setPath("/");
        response.addCookie(ticketChoice);
    }

    @Override
    public void createTicket(int seatNumber, Bus bus) {
        ticketRepository.createTicket(seatNumber, bus);
    }

    @Override
    public List<ReturnTicketsOfChosenTravelerDTO> findTicketsOfChosenTraveler(String login) {
        return ticketRepository.findTicketsOfChosenTraveler(login);
    }
    @Transactional
    @Override
    public void removeUserFromTicket(long ticketId,String login) {
        ticketRepository.removeUserFromTicket(ticketId,login);
    }

}
