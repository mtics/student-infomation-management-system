package tech.aspi.sims.college.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.college.model.College;

@RepositoryRestResource
public interface CollegeRepository extends CrudRepository<College, Integer> {

}
