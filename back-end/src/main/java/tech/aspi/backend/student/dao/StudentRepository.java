package tech.aspi.sims.student.dao;

import org.springframework.data.repository.CrudRepository;
import tech.aspi.sims.student.model.Student;

public interface StudentRepository extends CrudRepository<Student, String> {

    //Optional<Checkio> findById(ID stu_id);
}
