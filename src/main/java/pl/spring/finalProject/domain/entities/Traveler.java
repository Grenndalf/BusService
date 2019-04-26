package pl.spring.finalProject.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Table
@Entity
public class Traveler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    @Size(min = 3, max = 10, message = "login length has to be between 3 and 10")
    private String login;
    @NotNull(message = "can not be empty")
    @Size(min = 3, max = 10, message = "login length has to be between 3 and 10")
    private String firstName;
    @NotNull(message = "can not be empty")
    @Size(min = 3, max = 10, message = "login length has to be between 3 and 10")
    private String lastName;
    @NotNull(message = "can not be empty")
    private String password;


    @Override
    public String toString() {
        return "Traveler{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Traveler() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getfirstName() {
        return firstName;
    }

    public void setfirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getlastName() {
        return lastName;
    }

    public void setlastName(String lastName) {
        this.lastName = lastName;
    }

    public String getpassword() {
        return password;
    }

    public void setpassword(String password) {
        this.password = password;
    }
}
