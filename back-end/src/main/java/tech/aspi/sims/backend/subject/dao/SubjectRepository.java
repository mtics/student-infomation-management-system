package tech.aspi.sims.backend.subject.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.backend.subject.model.Subject;

@RepositoryRestResource
public interface SubjectRepository extends CrudRepository<Subject, Integer>, JpaSpecificationExecutor<Subject> {

    //Optional<Checkio> findById(ID stu_id);

    Iterable<Subject> findAllByMajorId(int majorId);
}
