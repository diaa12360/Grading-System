package com.grading.system.control;

import com.grading.system.model.Grades;
import com.grading.system.servises.InstructorServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/instructor")
@ComponentScan
public class InstructorController {
    private final InstructorServices instructorServices;

    @Autowired
    public InstructorController(InstructorServices instructorServices) {
        this.instructorServices = instructorServices;
    }

    @PutMapping("/add-grad-for-student")
    public ResponseEntity<Grades> assignMarkForStudent(@RequestParam int stdId,
                                                        @RequestParam int courseId,
                                                        @RequestParam int grad){
        Grades grades = instructorServices.setGradForStudent(stdId, courseId, grad);
        return new ResponseEntity<>(grades, HttpStatus.OK);
    }
}
