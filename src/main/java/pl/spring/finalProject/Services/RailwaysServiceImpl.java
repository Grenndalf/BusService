package pl.spring.finalProject.Services;

import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.RailwaysDTO;
import pl.spring.finalProject.Repositories.RailwaysRepository;
import pl.spring.finalProject.domain.entities.Railways;

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
        List<RailwaysDTO> result = railwaysRepository.findAll()
                .stream()
                .map(Converters::convertRailways)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public Railways findRailwaysBasedOnRailwayDTO(RailwaysDTO railwaysDTO) {
        Railways result = railwaysRepository.findByCityAndRailwayAddress(railwaysDTO.getCity(),railwaysDTO.getRailwayAddress());
        return result;
    }
}
