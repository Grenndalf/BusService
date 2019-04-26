package pl.spring.finalProject.DTOs;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TravelerDTO {

    @NotNull(message = "can not be empty")
    @Size(min = 3, max = 10,message = "length has to be between 3 and 10")
    private String login;
    @NotNull(message = "can not be empty")
    @Size(min = 3, max = 10, message = "length has to be between 3 and 10")
    private String firstName;
    @NotNull(message = "can not be empty")
    @Size(min = 3, max = 10, message = "length has to be between 3 and 10")
    private String lastName;
    @NotNull(message = "can not be empty")
    @NotBlank
    private String password;

    public TravelerDTO() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
