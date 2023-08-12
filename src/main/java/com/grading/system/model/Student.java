package com.grading.system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.io.Serializable;

@Data
@Entity
public class Student implements Serializable {
    @Id
    @Column(
            name="std_id",
            columnDefinition = "int",
            unique = true
    )
    private int id;
    @Column(
            name="first_name",
            columnDefinition = "TEXT"
    )
    private String firstName;
    @Column(
            name="last_name",
            columnDefinition = "TEXT"
    )
    private String lastName;
    @Column(
            name="password",
            columnDefinition = "TEXT"
    )
    private String password;


}
