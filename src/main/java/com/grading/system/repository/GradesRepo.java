package com.grading.system.repository;

import com.grading.system.model.GradeId;
import com.grading.system.model.Grades;
import com.grading.system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryDefinition(domainClass = Grades.class, idClass = GradeId.class)
@EnableJpaRepositories(basePackages = "org.grading.system.model")
public interface GradesRepo extends JpaRepository<Grades, GradeId> {
    List<Grades> findAllByGradeIdStudent(Student student);
}
