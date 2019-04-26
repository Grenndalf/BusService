package pl.spring.finalProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.BusDTO;
import pl.spring.finalProject.Repositories.BusRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusServiceImpl implements BusService {

    @Autowired
    private BusRepository busRepository;

    @Override
    public List<BusDTO> busListConverter() {
        List<BusDTO> busList = busRepository.findAll()
                .stream()
                .map(Converters::convertBuses)
                .collect(Collectors.toList());
        return busList;
    }

}

