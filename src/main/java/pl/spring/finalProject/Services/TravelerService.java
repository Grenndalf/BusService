package pl.spring.finalProject.Services;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.TravelerDTO;

@Service
public interface TravelerService {

    public void saveNewTraveler(TravelerDTO travelerDTO);

    boolean isAvailable(String login);
}
