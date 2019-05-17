package pl.spring.finalProject.DTOs;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import pl.spring.finalProject.Repositories.TravelerRepository;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SomeSimpleClassForLogIn implements AuthenticationManager {
    @Autowired
    private TravelerRepository travelerRepository;

    private String login;
    private String password;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }
}
