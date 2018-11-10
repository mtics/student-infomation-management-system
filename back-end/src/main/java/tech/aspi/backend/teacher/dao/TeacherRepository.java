package tech.aspi.sims.teacher.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.teacher.model.Teacher;

@RepositoryRestResource
public interface TeacherRepository extends CrudRepository<Teacher, String> {

}
