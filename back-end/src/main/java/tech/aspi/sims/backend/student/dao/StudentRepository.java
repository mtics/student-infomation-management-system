package tech.aspi.sims.backend.student.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.backend.student.model.Student;

@RepositoryRestResource
public interface StudentRepository extends CrudRepository<Student, String>, JpaSpecificationExecutor<Student> {
}
