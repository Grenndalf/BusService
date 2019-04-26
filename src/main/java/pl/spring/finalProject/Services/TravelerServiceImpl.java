package pl.spring.finalProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.Repositories.TravelerRepository;

@Service
public class TravelerServiceImpl implements TravelerService {

    @Autowired
    private TravelerRepository travelerRepository;

    @Override
    public void saveNewTraveler(TravelerDTO travelerDTO) {
        travelerRepository.save(Converters.convertTravelerDTO(travelerDTO));
    }
}
