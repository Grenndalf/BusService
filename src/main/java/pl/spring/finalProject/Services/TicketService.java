package pl.spring.finalProject.Services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;

@Service
public interface TicketService {

    ArrayList<Integer> seats(long id);

    ResponseEntity<String> saveTicket(HttpServletRequest request, Principal principal, Integer seatNumber);

    void cookieBusIdCreated(long id, HttpServletResponse response);
}
