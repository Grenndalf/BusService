package pl.spring.finalProject.DTOs;

import lombok.*;

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
    private String title;

    @NotNull
    @NotBlank
    @Column (columnDefinition="TEXT")
    private String content;

    private LocalDate createdAt= LocalDate.now();
}
