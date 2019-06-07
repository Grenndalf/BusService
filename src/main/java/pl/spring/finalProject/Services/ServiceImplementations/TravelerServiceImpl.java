package pl.spring.finalProject.Services.ServiceImplementations;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.spring.finalProject.DTOs.TravelerDTO;
import pl.spring.finalProject.Repositories.TravelerRepository;
import pl.spring.finalProject.Services.Converters;
import pl.spring.finalProject.Services.TravelerService;
import pl.spring.finalProject.domain.entities.Traveler;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TravelerServiceImpl implements TravelerService {

    private final TravelerRepository travelerRepository;

    private final PasswordEncoder passwordEncoder;

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
    public HashMap getfirstandlastnameandemailbylogin(String login) {
        return travelerRepository.getFirstAndLastnameAndEmailByLogin(login);
    }

    @Override
    public void updateUserFirstName(String firstName, String login) {
        travelerRepository.updateUserFirstName(firstName,login);
    }

    @Override
    public void updateUserLastName(String lastName, String login) {
        travelerRepository.updateUserLastName(lastName,login);
    }

    @Override
    public void updateUserEmail(String email, String login) {
        travelerRepository.updateUserEmail(email,login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Traveler traveler = travelerRepository.findByLogin(username);
        if (traveler != null){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(traveler.getLogin(),traveler.getPassword(),authorities);
        }
        throw new UsernameNotFoundException("Bad credentials");
    }
}
