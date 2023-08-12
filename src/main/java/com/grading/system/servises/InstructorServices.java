package com.grading.system.servises;

import com.grading.system.exception.CourseException;
import com.grading.system.exception.InstructorException;
import com.grading.system.exception.StudentException;
import com.grading.system.model.*;
import com.grading.system.repository.CourseRepo;
import com.grading.system.repository.GradesRepo;
import com.grading.system.repository.InstructorRepo;
import com.grading.system.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InstructorServices {
    private final InstructorRepo instructorRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final GradesRepo gradesRepo;


    @Autowired
    public InstructorServices(InstructorRepo instructorRepo, StudentRepo studentRepo, CourseRepo courseRepo, GradesRepo gradesRepo) {
        this.instructorRepo = instructorRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.gradesRepo = gradesRepo;
    }

    public Grades setGradForStudent(int stdId, int coursedId, int grad) {
        Student student = studentRepo.findById(stdId).orElseThrow(
                () -> new StudentException("Student is not exist!")
        );
        Course course = courseRepo.findById(coursedId).orElseThrow(
                () -> new CourseException("course is not exist!")
        );
        Grades grade = gradesRepo.findById(new GradeId(student, course)).orElseThrow(
                () -> new InstructorException("This Student is not registered in this course!!")
        );
        grade.setGrade(grad);
        return grade;
    }

    public List<Course> getCoursesForInstructor(int stuffId) {
        Instructor id = instructorRepo.findById(stuffId).orElseThrow(
                () -> new InstructorException("Instructor Not Found")
        );
        return courseRepo.findAllByInstructorId(id);
    }
}
