package pl.spring.finalProject.DTOs;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AnnouncementsDTO {

    @NotNull
    @NotBlank
    @Length(max=150)
    private String title;

    @NotNull
    @NotBlank
    @Length(max = 1500)
    @Column (columnDefinition="TEXT")
    private String content;

    private LocalDate createdAt= LocalDate.now();
}
