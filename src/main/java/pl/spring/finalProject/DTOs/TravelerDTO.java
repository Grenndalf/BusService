package pl.spring.finalProject.DTOs;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import pl.spring.finalProject.Services.validationGroup;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class TravelerDTO {

    @NotNull(message = "can not be empty")
    @Size(min = 3, max = 10,message = "length has to be between 3 and 10")
    private String login;
    @NotNull(message = "can not be empty",groups = validationGroup.class)
    @Size(min = 3, max = 10, message = "length has to be between 3 and 10",groups = {validationGroup.class, Default.class})
    private String firstName;
    @NotNull(message = "can not be empty",groups = validationGroup.class)
    @Size(min = 3, max = 10, message = "length has to be between 3 and 10",groups = {validationGroup.class, Default.class})
    private String lastName;
    @NotNull(message = "can not be empty")
    @NotBlank
    private String password;
    @NotBlank
    @NotNull(groups = {validationGroup.class, Default.class})
    @Email(groups = {validationGroup.class, Default.class})
    private String email;
}
