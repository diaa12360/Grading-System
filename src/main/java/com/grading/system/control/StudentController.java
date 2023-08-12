package com.grading.system.control;


import com.grading.system.model.Course;
import com.grading.system.servises.StudentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    private final StudentServices studentServices;

    @Autowired
    public StudentController(StudentServices studentServices) {
        this.studentServices = studentServices;
    }

    @GetMapping("/grads/{id}")
    public ResponseEntity<Map<Course, Integer>> coursesWithGrades(@PathVariable("id") Integer stdId) {
        Map<Course, Integer> grads = studentServices.getStudentCoursesAndGrades(stdId);
        return new ResponseEntity<>(grads, HttpStatus.OK);
    }
}
