package pl.spring.finalProject.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table ( name = "Railways" )
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Railways {

    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private long id;
    private String city;
    private String railwayAddress;

}
