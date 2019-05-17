package pl.spring.finalProject.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 10, message = "login length has to be between 3 and 10")
    private String login;
    @NotNull(message = "can not be empty")
    @Size ( min = 3, max = 10, message = "first name length has to be between 3 and 10" )
    private String firstName;
    @NotNull(message = "can not be empty")
    @Size ( min = 3, max = 10, message = "last Name length has to be between 3 and 10" )
    private String lastName;
    @NotNull(message = "can not be empty")
    @NotBlank
    private String password;
    @NotNull
    @Email
    private String email;
}
