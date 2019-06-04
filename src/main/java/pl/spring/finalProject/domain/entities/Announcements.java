package pl.spring.finalProject.domain.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table
//        (uniqueConstraints={@UniqueConstraint(columnNames = {"end_point_id","start_point_id"})})
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
    private String title;

    @NotNull
    @NotBlank
    @Column(columnDefinition="TEXT")
    private String content;

    private LocalDate createdAt = LocalDate.now();
}
