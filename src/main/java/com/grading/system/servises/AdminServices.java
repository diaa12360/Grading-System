package com.grading.system.servises;


import com.grading.system.exception.AdminException;
import com.grading.system.exception.CourseException;
import com.grading.system.exception.InstructorException;
import com.grading.system.exception.StudentException;
import com.grading.system.model.*;
import com.grading.system.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AdminServices {

    private final InstructorRepo instructorRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;
    private final AdminRepo adminRepo;
    private final GradesRepo gradesRepo;

    @Autowired
    public AdminServices(InstructorRepo instructorRepo, StudentRepo studentRepo, CourseRepo courseRepo, AdminRepo adminRepo, GradesRepo gradesRepo) {
        this.instructorRepo = instructorRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
        this.adminRepo = adminRepo;
        this.gradesRepo = gradesRepo;
    }

    public boolean verifyAdmin(int id) {
        adminRepo.findById(id).orElseThrow(
                () -> new AdminException("User by id " + id + " was not found")
        );
        return true;
    }

    public boolean checkStudent(int id) {
        return studentRepo.findById(id).isPresent();
    }

    public boolean login(int id, String pwd) {
        return pwd.equals(adminRepo.findById(id).orElseThrow(
                () -> new AdminException("Wrong ID or Password")
        ).getPassword());
    }

    public Student addStudent(Student student) {
        return studentRepo.save(student);
    }

    public Instructor addInstructor(Instructor instructor) {
        return instructorRepo.save(instructor);
    }

    public Course addCourse(Course course) {
        return courseRepo.save(course);
    }

    public Grades assignStudentToCourse(int stdId, int courseId) {
        Student student = studentRepo.findById(stdId).orElseThrow(
                () -> new StudentException("Student Not Found!")
        );
        Course course = courseRepo.findById(courseId).orElseThrow(
                () -> new InstructorException("Course Not Found!")
        );
        GradeId id = new GradeId(student, course);
        if (gradesRepo.findById(id).isPresent()) {
            throw new StudentException("Student is Already Registered in this course!!");
        }
        Grades grades = new Grades();
        grades.setGradeId(new GradeId(student, course));
        gradesRepo.save(grades);
        return grades;
    }

    public Course assignInstructorToCourse(int stuffId, int courseId) {
        Course course = courseRepo.findById(courseId).orElseThrow(
                () -> new InstructorException("Course Not Found!")
        );
        if (course.getInstructorId().getId() == stuffId) {
            throw new CourseException("Instructor is already in this course!");
        } else {
            Instructor instructor = instructorRepo.findById(course.getInstructorId().getId()).orElseThrow(
                    () -> new InstructorException("Instructor Not Found!")
            );
            course.setInstructorId(instructor);
            courseRepo.save(course);
        }
        return course;
    }

    public void deleteCourse(int deleteId) {
        courseRepo.deleteById(deleteId);
    }

    public Student updateStudent(Student student) {
        return studentRepo.save(student);
    }

    public Course updateCourse(Course course) {
        return courseRepo.save(course);
    }

    public Instructor updateInstructor(Instructor instructor) {
        return instructorRepo.save(instructor);
    }

    public boolean checkInstructor(int id) {
        return instructorRepo.findById(id).isPresent();
    }
}
