package pl.spring.finalProject.web.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.DTOs.ReturnResultFromGetConnectionsMethodDTO;
import pl.spring.finalProject.Services.BusService;
import pl.spring.finalProject.Services.Converters;
import pl.spring.finalProject.Services.TicketService;
import pl.spring.finalProject.domain.entities.Bus;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@RestController
public class BusController {

    private BusService busService;
    private TicketService ticketService;

    public BusController(BusService busService, TicketService ticketService) {
        this.busService = busService;
        this.ticketService = ticketService;
    }

    @CrossOrigin
    @RequestMapping("/getConnections")
    public List<ReturnResultFromGetConnectionsMethodDTO> getConnections(@Valid @RequestBody BusDTO busDTO) {
        return busService.findFullInfo(busDTO);
    }

    @RequestMapping ( "/getData" )
    @ResponseBody
    public BusDTO getChosenConnection(HttpServletRequest request) {
        Cookie id = WebUtils.getCookie(request, "ticketChoice");
        if (id != null) {
            return Converters.convertBuses(busService.findBusById(Long.valueOf(id.getValue())));
        } else {
            return new BusDTO();
        }
    }

    @Transactional
    @RequestMapping ( "/newConnection" )
    public ResponseEntity<String> newConnection(@Valid @RequestBody BusDTO busDTO) {
        if (busService.isBusConnectionAlreadyExist(busDTO)) {
            return ResponseEntity.badRequest().body("this connection already exist");
        }
        busService.saveBus2(busService.ConvertBusDTOBeforeSave(busDTO));
        Bus bus = busService.findBus(busDTO);
        for (int i =1;i<=busDTO.getMaxNumberOfSeatsAvailable();i++){
            ticketService.createTicket(i,bus);
        }
        return ResponseEntity.ok("Connection saved, Tickets created");
    }
}
