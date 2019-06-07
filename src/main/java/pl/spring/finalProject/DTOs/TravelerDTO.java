package pl.spring.finalProject.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.validation.constraints.*;
import javax.validation.groups.Default;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TravelerDTO {
    @Pattern ( regexp ="^(?=.*[A-Za-z0-9]$)[A-Za-z][A-Za-z\\d.-]{3,10}$",message = "length has to be between 3 and 10 and contains only letters and digits"  )
    @Column (unique = true, nullable = false)
    private String login;
    @Pattern ( regexp ="[A-Za-z]{3,10}$", message = "length has to be between 3 and 10 and contains only letters" )
    private String firstName;
    @Pattern ( regexp ="[A-Za-z]{3,10}$",  message = "length has to be between 3 and 10 and contains only letters")
    private String lastName;
    @NotNull(message = "can not be empty")
    @NotBlank
    private String password;

    @NotBlank
    @NotNull
    @Email
    private String email;
}
