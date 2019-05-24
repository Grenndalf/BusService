package pl.spring.finalProject.Services.ServiceImplementations;

import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.RailwaysDTO;
import pl.spring.finalProject.Repositories.RailwaysRepository;
import pl.spring.finalProject.Services.Converters;
import pl.spring.finalProject.Services.RailwaysService;
import pl.spring.finalProject.domain.entities.Railways;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RailwaysServiceImpl implements RailwaysService {
    private RailwaysRepository railwaysRepository;

    public RailwaysServiceImpl(RailwaysRepository railwaysRepository) {
        this.railwaysRepository = railwaysRepository;
    }

    @Override
    public List<RailwaysDTO> railwaysListConverter() {


        return railwaysRepository.findAll()
                .stream()
                .map(Converters::convertRailways)
                .sorted(Comparator.comparing(RailwaysDTO::getCity))
                .collect(Collectors.toList());
    }

    @Override
    public Railways findRailwaysBasedOnRailwayDTO(RailwaysDTO railwaysDTO) {
        return railwaysRepository.findByCityAndRailwayAddress(railwaysDTO.getCity(), railwaysDTO.getRailwayAddress());
    }
}
