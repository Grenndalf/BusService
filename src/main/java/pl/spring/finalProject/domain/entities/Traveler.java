package pl.spring.finalProject.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Traveler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Pattern ( regexp ="^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{3,10}$",message = "length has to be between 3 and 10 and contains only letters and digits"  )
    @Column(unique = true, nullable = false)
    private String login;
    @Pattern ( regexp ="[A-Za-z]{3,10}$",message = "length has to be between 3 and 10 and contains only letters"  )
    private String firstName;
    @Pattern ( regexp ="[A-Za-z]{3,10}$",message = "length has to be between 3 and 10 and contains only letters"  )
    private String lastName;

    @NotNull(message = "can not be empty")
    @NotBlank
    private String password;
    
    @NotNull
    @Email
    private String email;
}
