package pl.spring.finalProject.Services;

import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.Repositories.BusRepository;
import pl.spring.finalProject.domain.entities.Bus;
import pl.spring.finalProject.domain.entities.Railways;

import java.util.ArrayList;
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
    public List<BusDTO> busListConverter(List<Bus> busList) {
        List<BusDTO> busDTOList = busList
                .stream()
                .map(Converters::convertBuses)
                .collect(Collectors.toList());
        return busDTOList;
    }
    @Override
    public List<BusDTO> getBusDTOS(Railways start, Railways end, String date) {
        return busListConverter(busRepository.findConnections(start.getId(),end.getId(),date));
    }


}

