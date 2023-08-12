package com.grading.system.model;


import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Course implements Serializable {
    @Id
    @Column(
            name = "course_id",
            columnDefinition = "int",
            unique = true
    )
    private Integer id;
    @Column(
            name = "course_name",
            columnDefinition = "TEXT"
    )
    private String courseName;
    @ManyToOne(targetEntity = Instructor.class)
    @JoinColumn(name = "instructor_id")
    private Instructor instructorId;

}

