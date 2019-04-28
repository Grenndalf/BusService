package pl.spring.finalProject.Services;

import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.DTOs.ReturnResultFromGetConnetionsMethodDTO;
import pl.spring.finalProject.domain.entities.Bus;
import pl.spring.finalProject.domain.entities.Railways;

import java.util.List;
@Service
public interface BusService {

    public List<BusDTO> busListConverter(List<Bus> busList);
    public List<BusDTO> getBusDTOS(Railways start, Railways end, String date);
    List<ReturnResultFromGetConnetionsMethodDTO> findfullinfo(BusDTO busDTO);
}
