package tn.esprit.studentmanagement.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.services.IEnrollment;
import tn.esprit.studentmanagement.entities.EnrollmentDTO;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/Enrollment")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class EnrollmentController {

    IEnrollment enrollmentService;

    @GetMapping("/getAllEnrollment")
    public List<Enrollment> getAllEnrollment() {
        return enrollmentService.getAllEnrollments();
    }

    @GetMapping("/getEnrollment/{id}")
    public EnrollmentDTO getEnrollment(@PathVariable Long id) {
        Enrollment enrollment = enrollmentService.getEnrollmentById(id);

        EnrollmentDTO dto = new EnrollmentDTO();
        dto.setId(enrollment.getIdEnrollment());        // ✅ idEnrollment au lieu de id
        dto.setGrade(enrollment.getGrade());            // ✅ champ qui existe
        dto.setEnrollmentDate(enrollment.getEnrollmentDate()); // ✅ champ qui existe

        return dto;
    }

    @PostMapping("/createEnrollment")
    public EnrollmentDTO createEnrollment(@RequestBody EnrollmentDTO dto) {
        Enrollment enrollment = new Enrollment();
        enrollment.setGrade(dto.getGrade());                        // ✅
        enrollment.setEnrollmentDate(dto.getEnrollmentDate());      // ✅

        Enrollment savedEnrollment = enrollmentService.saveEnrollment(enrollment);

        EnrollmentDTO response = new EnrollmentDTO();
        response.setId(savedEnrollment.getIdEnrollment());          // ✅
        response.setGrade(savedEnrollment.getGrade());              // ✅
        response.setEnrollmentDate(savedEnrollment.getEnrollmentDate()); // ✅

        return response;
    }

    @PutMapping("/updateEnrollment/{id}")
public EnrollmentDTO updateEnrollment(@PathVariable Long id, @RequestBody EnrollmentDTO dto) {
    
    Enrollment enrollment = enrollmentService.getEnrollmentById(id);
    
    enrollment.setGrade(dto.getGrade());
    enrollment.setEnrollmentDate(dto.getEnrollmentDate());

    Enrollment updatedEnrollment = enrollmentService.saveEnrollment(enrollment);

    EnrollmentDTO response = new EnrollmentDTO();
    response.setId(updatedEnrollment.getIdEnrollment());
    response.setGrade(updatedEnrollment.getGrade());
    response.setEnrollmentDate(updatedEnrollment.getEnrollmentDate());

    return response;
}

@DeleteMapping("/deleteEnrollment/{id}")
public void deleteEnrollment(@PathVariable Long id) {
    enrollmentService.deleteEnrollment(id);
}
}
