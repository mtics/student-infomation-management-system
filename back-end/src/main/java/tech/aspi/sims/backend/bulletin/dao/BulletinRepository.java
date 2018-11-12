package tech.aspi.sims.backend.bulletin.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.backend.bulletin.model.Bulletin;

@RepositoryRestResource
public interface BulletinRepository extends CrudRepository<Bulletin, Integer> {

    //Optional<Checkio> findById(ID stu_id);
}
