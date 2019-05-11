package pl.spring.finalProject.Services;

import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.domain.entities.Traveler;

import java.util.HashMap;

@Service
public interface TravelerService {

    void saveNewTraveler(TravelerDTO travelerDTO);

    boolean isAvailable(String login);

    Traveler findbyLogin(String login);

    HashMap getFirstAndLastName(String login);

    TravelerDTO getTravelerData(String login);

}
