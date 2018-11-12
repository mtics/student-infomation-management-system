package tech.aspi.sims.backend.subject.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.backend.subject.model.Subject;

@RepositoryRestResource
public interface SubjectRepository extends CrudRepository<Subject, Integer> {

    //Optional<Checkio> findById(ID stu_id);
}
