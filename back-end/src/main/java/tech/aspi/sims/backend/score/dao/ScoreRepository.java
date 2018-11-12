package tech.aspi.sims.backend.score.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.backend.score.model.Score;

@RepositoryRestResource
public interface ScoreRepository extends CrudRepository<Score, Integer> {

    //Optional<Checkio> findById(ID stu_id);
}
