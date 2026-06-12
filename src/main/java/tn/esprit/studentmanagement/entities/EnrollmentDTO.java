package tn.esprit.studentmanagement.entities;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class EnrollmentDTO {
    private Long id;                  // ✅ ajouté
    private LocalDate enrollmentDate; // ✅ ajouté
    private Double grade;             // ✅ ajouté
}