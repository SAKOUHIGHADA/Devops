package tn.esprit.studentmanagement.controllers;

import lombok.AllArgsConstructor;

import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.services.IStudentService;
import tn.esprit.studentmanagement.entities.StudentDTO;


import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:4200")
@AllArgsConstructor
public class StudentController {

    IStudentService studentService;

    @GetMapping("/getAllStudents")
    public List<StudentDTO> getAllStudents() {
        return studentService.getAllStudents()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/getStudent/{id}")
    public StudentDTO getStudent(@PathVariable Long id) {
        return toDTO(studentService.getStudentById(id));
    }

    @PostMapping("/createStudent")
    public StudentDTO createStudent(@RequestBody StudentDTO dto) {
        return toDTO(studentService.saveStudent(toEntity(dto)));
    }

    @PutMapping("/updateStudent")
    public StudentDTO updateStudent(@RequestBody StudentDTO dto) {
        return toDTO(studentService.saveStudent(toEntity(dto)));
    }

    @DeleteMapping("/deleteStudent/{id}")
    public void deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }

    // ---- Conversion DTO <-> Entity ----

   private StudentDTO toDTO(Student student) {
    StudentDTO dto = new StudentDTO();
    dto.setIdStudent(student.getIdStudent());
    dto.setFirstName(student.getFirstName());
    dto.setLastName(student.getLastName());
    dto.setEmail(student.getEmail());
    return dto;
    }

    private Student toEntity(StudentDTO dto) {
    Student student = new Student();
    student.setIdStudent(dto.getIdStudent());
    student.setFirstName(dto.getFirstName());
    student.setLastName(dto.getLastName());
    student.setEmail(dto.getEmail());
    return student;
    }
}