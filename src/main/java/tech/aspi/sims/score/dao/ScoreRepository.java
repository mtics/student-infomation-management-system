package tech.aspi.sims.score.dao;

import org.springframework.data.repository.CrudRepository;
import tech.aspi.sims.score.model.Score;

public interface ScoreRepository extends CrudRepository<Score, Long> {

    //Optional<Checkio> findById(ID stu_id);
}
