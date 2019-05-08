package pl.spring.finalProject.web.Controllers;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.WebUtils;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.DTOs.ReturnResultFromGetConnectionsMethodDTO;
import pl.spring.finalProject.Services.BusService;
import pl.spring.finalProject.Services.Converters;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
public class BusController {

    private BusService busService;

    public BusController(BusService busService) {
        this.busService = busService;
    }

    @CrossOrigin
    @RequestMapping("/getConnections")
    public List<ReturnResultFromGetConnectionsMethodDTO> getConnections(@Valid @RequestBody BusDTO busDTO, Principal principal) {
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
}
