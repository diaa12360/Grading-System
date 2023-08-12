package com.grading.system.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class GradeId implements Serializable {
    @JoinColumn(name = "std_id")
    @ManyToOne(targetEntity = Student.class)
    private Student student;
    @JoinColumn(name = "course_id")
    @ManyToOne(targetEntity = Course.class)
    private Course course;

    public GradeId(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public GradeId() {
    }
}
