package tn.esprit.studentmanagement.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.studentmanagement.entities.EnrollmentDTO;
import tn.esprit.studentmanagement.services.IEnrollment;

import java.util.List;

@RestController
@RequestMapping("/Enrollment")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class EnrollmentController {
    IEnrollment enrollmentService;
    @GetMapping("/getAllEnrollment")
    public List<Enrollment> getAllEnrollment() { return enrollmentService.getAllEnrollments(); }

  @GetMapping("/getEnrollment/{id}")
public EnrollmentDTO getEnrollment(@PathVariable Long id) {

    Enrollment enrollment = enrollmentService.getEnrollmentById(id);

    EnrollmentDTO dto = new EnrollmentDTO();
    dto.setId(enrollment.getId());
    dto.setAcademicYear(enrollment.getAcademicYear());

    return dto;
}

@PostMapping("/createEnrollment")
public EnrollmentDTO createEnrollment(@RequestBody EnrollmentDTO dto) {

    Enrollment enrollment = new Enrollment();
    enrollment.setAcademicYear(dto.getAcademicYear());

    Enrollment savedEnrollment = enrollmentService.saveEnrollment(enrollment);

    EnrollmentDTO response = new EnrollmentDTO();
    response.setId(savedEnrollment.getId());
    response.setAcademicYear(savedEnrollment.getAcademicYear());

    return response;
}

    @PutMapping("/updateEnrollment")
    public Enrollment updateEnrollment(@RequestBody Enrollment enrollment) {
        return enrollmentService.saveEnrollment(enrollment);
    }

    @DeleteMapping("/deleteEnrollment/{id}")
    public void deleteEnrollment(@PathVariable Long id) {
        enrollmentService.deleteEnrollment(id); }
}
