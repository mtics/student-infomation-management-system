package tech.aspi.sims.backend.major.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.backend.major.model.Major;

@RepositoryRestResource
public interface MajorRepository extends CrudRepository<Major, Integer> {

}
