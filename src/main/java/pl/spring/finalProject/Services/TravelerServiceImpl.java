package pl.spring.finalProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.Repositories.TravelerRepository;

@Service
public class TravelerServiceImpl implements TravelerService {

    @Autowired
    private TravelerRepository travelerRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void saveNewTraveler(TravelerDTO travelerDTO) {
        travelerDTO.setPassword(passwordEncoder.encode(travelerDTO.getPassword()));
        travelerRepository.save(Converters.convertTravelerDTO(travelerDTO));
    }

    @Override
    public boolean isAvailable(String login){
        return travelerRepository.isTravelerLoginIsAvailable(login);
    }
}
