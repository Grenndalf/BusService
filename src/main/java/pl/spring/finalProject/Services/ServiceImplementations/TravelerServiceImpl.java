package pl.spring.finalProject.Services.ServiceImplementations;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.Repositories.TravelerRepository;
import pl.spring.finalProject.Services.Converters;
import pl.spring.finalProject.Services.TravelerService;
import pl.spring.finalProject.domain.entities.Traveler;

import javax.transaction.Transactional;
import java.util.HashMap;

@Service
public class TravelerServiceImpl implements TravelerService {


    private TravelerRepository travelerRepository;

    private PasswordEncoder passwordEncoder;

    public TravelerServiceImpl(TravelerRepository travelerRepository, PasswordEncoder passwordEncoder) {
        this.travelerRepository = travelerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveNewTraveler(TravelerDTO travelerDTO) {
        travelerDTO.setPassword(passwordEncoder.encode(travelerDTO.getPassword()));
        travelerRepository.save(Converters.convertTravelerDTO(travelerDTO));
    }

    @Override
    public boolean isAvailable(String login){
        return travelerRepository.isTravelerLoginIsAvailable(login);
    }

    @Override
    public Traveler findbyLogin(String login) {
        return travelerRepository.findByLogin(login);

    }

    @Override
    public HashMap getFirstAndLastName(String login) {
        return travelerRepository.getFirstAndLastnameByLogin(login);
    }

    @Override
    public TravelerDTO getTravelerData(String login) {
        return Converters.convertTraveler(travelerRepository.getTravelerData(login));
    }

    @Override
    public HashMap getFirstAndLastnameAndEmailByLogin(String login) {
        return travelerRepository.getFirstAndLastnameAndEmailByLogin(login);
    }
    @Transactional
    @Override
    public void updateUserData(String firstName, String lastName, String email, String login) {
        travelerRepository.updateUserData(firstName, lastName, email, login);
    }

}
