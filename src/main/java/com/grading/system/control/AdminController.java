package com.grading.system.control;

import com.grading.system.model.Course;
import com.grading.system.model.Grades;
import com.grading.system.model.Instructor;
import com.grading.system.model.Student;
import com.grading.system.servises.AdminServices;
import com.grading.system.servises.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
@ComponentScan
public class AdminController {
    private final AdminServices adminServices;

    @Autowired
    public AdminController(AdminServices adminServices) {
        this.adminServices = adminServices;
    }

    //TO DO: update student
    @PutMapping("/update-student")
    public ResponseEntity<Student> updateStudent(@RequestBody Student student) {
        student.setPassword(Encryption.encrypt(student.getPassword()));
        Student updateStudent = adminServices.updateStudent(student);
        return new ResponseEntity<>(updateStudent, HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, value = "/add-student")
    public ResponseEntity<Student> addStudent(@RequestBody final Student student) {
        student.setPassword(Encryption.encrypt(student.getPassword()));
        Student newStudent = adminServices.updateStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.OK);
    }

    //TO DO: assign student
    @PutMapping("/assign-student-to-course")
    public ResponseEntity<Grades> assign(@RequestParam(name = "std_id") Integer studentId,
                                          @RequestParam(name = "course_id") Integer courseId) {
        Grades grades = adminServices.assignStudentToCourse(studentId, courseId);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }

    //TO DO: add student
    @PostMapping("/add-instructor")
    public ResponseEntity<Instructor> addStudent(@RequestBody Instructor instructor) {
        instructor.setPassword(Encryption.encrypt(instructor.getPassword()));
        Instructor newInstructor = adminServices.addInstructor(instructor);
        return new ResponseEntity<>(newInstructor, HttpStatus.OK);
    }

    //TO DO: add Course
    @PostMapping("/add-course")
    public ResponseEntity<Course> addStudent(@RequestBody Course course) {
        Course newCourse = adminServices.addCourse(course);
        return new ResponseEntity<>(newCourse, HttpStatus.OK);
    }

    //TO DO: add Course
    @PutMapping("/assign-instructor-to-course")
    public ResponseEntity<Course> addStudent(@RequestParam(name = "stuff_id") Integer instructorId,
                                                 @RequestParam(name = "course_id") Integer courseId) {
        Course newCourse = adminServices.assignInstructorToCourse(instructorId, courseId);
        return new ResponseEntity<>(newCourse, HttpStatus.OK);
    }

}
