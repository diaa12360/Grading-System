package com.grading.system.servises;

import com.grading.system.exception.CourseException;
import com.grading.system.exception.StudentException;
import com.grading.system.model.Course;
import com.grading.system.model.Grades;
import com.grading.system.model.Student;
import com.grading.system.repository.CourseRepo;
import com.grading.system.repository.GradesRepo;
import com.grading.system.repository.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class StudentServices {
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final GradesRepo gradesRepo;

    @Autowired
    public StudentServices(StudentRepo studentRepo, CourseRepo courseRepo, GradesRepo gradesRepo) {
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.gradesRepo = gradesRepo;
    }

    public Student getStudentById(int stdId) {
        return studentRepo.findById(stdId).orElseThrow(
                () -> new StudentException("Student Not Found")
        );
    }

    public Map<Course, Integer> getStudentCoursesAndGrades(int stdId) {
        Student student = studentRepo.findById(stdId).orElseThrow(
                () -> new StudentException("Student Not Found")
        );
        List<Grades> grades = gradesRepo.findAllByGradeIdStudent(student);
        Map<Course, Integer> courseGrade = new HashMap<>();
        for (Grades grade: grades) {
            Course course = courseRepo.findById(grade.getGradeId().getCourse().getId()).orElseThrow(
                    () -> new CourseException("")
            );
            courseGrade.put(course, grade.getGrade());
        }
        return courseGrade;
    }

}
