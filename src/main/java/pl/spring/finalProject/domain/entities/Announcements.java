package pl.spring.finalProject.domain.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Announcements {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY )
    private long id;

    @NotNull
    @NotBlank
    @Length ( max = 150 )
    private String title;

    @NotNull
    @NotBlank
    @Length ( max = 1500 )
    @Column(columnDefinition="TEXT")
    private String content;

    private LocalDate createdAt = LocalDate.now();
}
