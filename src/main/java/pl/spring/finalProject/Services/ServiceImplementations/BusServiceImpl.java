package pl.spring.finalProject.Services.ServiceImplementations;

import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.DTOs.ReturnResultFromGetConnectionsMethodDTO;
import pl.spring.finalProject.Repositories.BusRepository;
import pl.spring.finalProject.Services.BusService;
import pl.spring.finalProject.Services.Converters;
import pl.spring.finalProject.Services.RailwaysService;
import pl.spring.finalProject.domain.entities.Bus;
import pl.spring.finalProject.domain.entities.Railways;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {


    private BusRepository busRepository;
    private RailwaysService railwaysService;

    public BusServiceImpl(BusRepository busRepository, RailwaysService railwaysService) {
        this.busRepository = busRepository;
        this.railwaysService = railwaysService;
    }
    @Override
    public Bus findBusById(long id) {
        return busRepository.findById(id);
    }
    @Override
    public List<BusDTO> busListConverter(List<Bus> busList) {
        List<BusDTO> busDTOList = busList
                .stream()
                .map(Converters::convertBuses)
                .collect(Collectors.toList());
        return busDTOList;
    }
    @Override
    public List<BusDTO> getBusDTOS(Railways start, Railways end, LocalDate date) {
        return busListConverter(busRepository.findConnections(start.getId(),end.getId(),date));
    }
    @Override
    public List<ReturnResultFromGetConnectionsMethodDTO> findFullInfo(BusDTO busDTO) {
        LocalDate day = busDTO.getTravelDate();
        String sc = busDTO.getStartPoint().getCity();
        String sra = busDTO.getStartPoint().getRailwayAddress();
        String ec = busDTO.getEndPoint().getCity();
        String era = busDTO.getEndPoint().getRailwayAddress();

        return busRepository.findFullInfo(sc, sra, ec, era, day).stream()
                .sorted(Comparator.comparing(ReturnResultFromGetConnectionsMethodDTO::getDepartureTime))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isBusConnectionAlreadyExist(BusDTO busDTO) {
        String spCity = busDTO.getStartPoint().getCity();
        String spRailwayAddress = busDTO.getStartPoint().getRailwayAddress();
        String epCity = busDTO.getEndPoint().getCity();
        String epRailwayAddress = busDTO.getEndPoint().getRailwayAddress();
        LocalDate travelDate = busDTO.getTravelDate();
        LocalTime departureTime = busDTO.getDepartureTime();
        return busRepository.isBusConnectionAlreadyExist2(spCity, spRailwayAddress, epCity, epRailwayAddress, travelDate, departureTime);
    }
    public Bus ConvertBusDTOBeforeSave (BusDTO busDTO){
        Bus bus = new Bus();
        bus.setStartPoint(railwaysService.findRailwaysBasedOnRailwayDTO(busDTO.getStartPoint()));
        bus.setEndPoint(railwaysService.findRailwaysBasedOnRailwayDTO(busDTO.getEndPoint()));
        bus.setDepartureTime(busDTO.getDepartureTime());
        bus.setTravelDate(busDTO.getTravelDate());
        bus.setMaxNumberOfSeatsAvailable(busDTO.getMaxNumberOfSeatsAvailable());
        return bus;
    }

    @Override
    public void saveBus(Bus bus) {
        busRepository.save(bus);
    }

    @Override
    public Bus findBus(BusDTO busDTO) {
        Railways startPoint = railwaysService.findRailwaysBasedOnRailwayDTO(busDTO.getStartPoint());
        Railways endPoint = railwaysService.findRailwaysBasedOnRailwayDTO(busDTO.getEndPoint());
        LocalDate travelDate = busDTO.getTravelDate();
        LocalTime departureTime = busDTO.getDepartureTime();
        return busRepository.findBus(startPoint,endPoint,travelDate,departureTime);
    }

    @Override
    public void saveBus2(Bus bus) {
        int mnosa = bus.getMaxNumberOfSeatsAvailable();
        LocalTime deptTime =bus.getDepartureTime();
        LocalDate travelDate = bus.getTravelDate();
        Railways ep = bus.getEndPoint();
        Railways sp = bus.getStartPoint();
        busRepository.saveBus2(mnosa,deptTime,travelDate,ep,sp);

    }

    public List<Bus> getAllBuses(){
        return busRepository.findAll();
    }
}

