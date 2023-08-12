package com.grading.system.repository;


import com.grading.system.model.Course;
import com.grading.system.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Course.class, idClass = Integer.class)
@EnableJpaRepositories(basePackages = "org.grading.system.model")
public interface CourseRepo extends JpaRepository<Course, Integer> {
    List<Course> findAllByInstructorId(Instructor id);

}
