package com.grading.system.model;


import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "grades")
public class Grades {
    @EmbeddedId
    private GradeId gradeId;
    @Column
    private Integer grade;
}

