package com.grading.system.repository;


import com.grading.system.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryDefinition(domainClass = Instructor.class, idClass = Integer.class)
@EnableJpaRepositories(basePackages = "org.grading.system.model")
public interface InstructorRepo extends JpaRepository<Instructor, Integer> {
}