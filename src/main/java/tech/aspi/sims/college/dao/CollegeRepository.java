package tech.aspi.sims.college.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.college.model.College;

import java.util.List;

@RepositoryRestResource
public interface CollegeRepository extends CrudRepository<College, Integer> {

}
