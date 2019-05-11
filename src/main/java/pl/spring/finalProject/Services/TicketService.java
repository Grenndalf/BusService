package pl.spring.finalProject.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.ReturnTicketsOfChosenTravelerDTO;
import pl.spring.finalProject.domain.entities.Bus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Service
public interface TicketService {

    ArrayList<Integer> seats(long id);

    ResponseEntity<String> saveTicket(HttpServletRequest request, Principal principal, Integer seatNumber);

    void cookieBusIdCreated(long id, HttpServletResponse response);

    void createTicket(int seatNumber, Bus bus);

    List<ReturnTicketsOfChosenTravelerDTO> findTicketsOfChosenTraveler(String login);

    void removeUserFromTicket(long ticketId,String login);

}
