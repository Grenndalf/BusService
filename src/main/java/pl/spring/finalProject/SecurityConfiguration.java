package pl.spring.finalProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.spring.finalProject.Repositories.TravelerRepository;
import pl.spring.finalProject.Services.ServiceImplementations.TravelerServiceImpl;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private DataSource dataSource;
    private final TravelerRepository travelerRepository;

    public SecurityConfiguration(DataSource dataSource, TravelerRepository travelerRepository) {
        this.dataSource = dataSource;
        this.travelerRepository = travelerRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}pass").roles("USER")
                .and()
                .withUser("admin").password("{noop}pass").roles("ADMIN");
//        auth.jdbcAuthentication()
//                .dataSource(dataSource)
//                .passwordEncoder(passwordEncoder())
//                .usersByUsernameQuery("SELECT login, password, true FROM traveler WHERE login = ?")
//                .authoritiesByUsernameQuery("SELECT login, 'ROLE_USER' FROM traveler WHERE login = ?");
        auth.userDetailsService(new TravelerServiceImpl(travelerRepository, passwordEncoder()));
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/searchConnections").authenticated()
                .antMatchers("/yourTicket").authenticated()
                .antMatchers("/AdminPanel", "/admin/**").hasRole("ADMIN")
                .antMatchers("/registerForm").permitAll()
                .antMatchers("/UserPanel").hasRole("USER")
//                .antMatchers("/**").authenticated()
                    .and()
                        .formLogin()
                            .loginPage("/login")
                            .defaultSuccessUrl("/default")
                            .failureForwardUrl("/logError").permitAll()
                    .and()
                        .logout()
                    .and()
                        .rememberMe()
                .key("verySecretKey").tokenValiditySeconds(30 * 24 * 60 * 60)
                    .and()
                        .csrf()
                            .disable();
    }
}
