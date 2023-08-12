package com.grading.system.repository;

import com.grading.system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RepositoryDefinition(domainClass = Student.class, idClass = Integer.class)
@EnableJpaRepositories(basePackages = "org.grading.system.model")
public interface StudentRepo extends JpaRepository<Student, Integer> {
}
