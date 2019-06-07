package pl.spring.finalProject.Services;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.domain.entities.Traveler;

import java.util.HashMap;

@Service
public interface TravelerService extends UserDetailsService {

    void saveNewTraveler(TravelerDTO travelerDTO);

    boolean isAvailable(String login);

    Traveler findbyLogin(String login);

    HashMap getFirstAndLastName(String login);

    TravelerDTO getTravelerData(String login);

    HashMap getfirstandlastnameandemailbylogin(String login);

    void updateUserFirstName(String firstName, String login);

    void updateUserLastName(String lastName, String login);

    void updateUserEmail(String email, String login);
}
