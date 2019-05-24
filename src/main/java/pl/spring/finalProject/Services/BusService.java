package pl.spring.finalProject.Services;

import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.DTOs.ReturnResultFromGetConnectionsMethodDTO;
import pl.spring.finalProject.domain.entities.Bus;
import pl.spring.finalProject.domain.entities.Railways;

import java.time.LocalDate;
import java.util.List;
@Service
public interface BusService {

    Bus findBusById(long id);
    public List<BusDTO> busListConverter(List<Bus> busList);
    public List<BusDTO> getBusDTOS(Railways start, Railways end, LocalDate date);

    List<ReturnResultFromGetConnectionsMethodDTO> findFullInfo(BusDTO busDTO);

    boolean isBusConnectionAlreadyExist(BusDTO busDTO);

    Bus ConvertBusDTOBeforeSave(BusDTO busDTO);

    void saveBus(Bus bus);

    Bus findBus(BusDTO busDTO);

    void saveBus2(Bus bus);

    List<Bus> getAllBuses();
}
