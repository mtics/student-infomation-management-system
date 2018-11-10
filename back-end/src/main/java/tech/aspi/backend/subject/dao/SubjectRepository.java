package tech.aspi.sims.subject.dao;

import org.springframework.data.repository.CrudRepository;
import tech.aspi.sims.subject.model.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Long> {

    //Optional<Checkio> findById(ID stu_id);
}
