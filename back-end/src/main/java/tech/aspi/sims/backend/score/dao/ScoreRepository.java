package tech.aspi.sims.backend.score.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.backend.score.model.Score;

import java.util.List;

@RepositoryRestResource
public interface ScoreRepository extends CrudRepository<Score, Integer>, JpaSpecificationExecutor<Score> {

}
