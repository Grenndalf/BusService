package pl.spring.finalProject.Services;

import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.TravelerDTO;

@Service
public interface TravelerService {

    public void saveNewTraveler(TravelerDTO travelerDTO);
}
