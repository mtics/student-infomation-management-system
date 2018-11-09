package tech.aspi.sims.bulletin.dao;

import org.springframework.data.repository.CrudRepository;
import tech.aspi.sims.bulletin.model.Bulletin;

public interface BulletinRepository extends CrudRepository<Bulletin, Integer> {

    //Optional<Checkio> findById(ID stu_id);
}
