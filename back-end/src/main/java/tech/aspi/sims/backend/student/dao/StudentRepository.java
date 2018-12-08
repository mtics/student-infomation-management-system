package tech.aspi.sims.backend.student.dao;

import org.apache.ibatis.annotations.Delete;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.backend.bulletin.model.Bulletin;
import tech.aspi.sims.backend.student.model.Student;

@RepositoryRestResource
public interface StudentRepository extends CrudRepository<Student, String>, JpaSpecificationExecutor<Student> {
}
