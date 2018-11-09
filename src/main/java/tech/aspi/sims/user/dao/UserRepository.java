package tech.aspi.sims.user.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import tech.aspi.sims.user.model.User;

import java.util.List;

@RepositoryRestResource
public interface UserRepository extends CrudRepository<User, String> {

    @Query(value = "select * from user where user_level = ?", nativeQuery = true)
    public List<User> queryByUserLevel(int user_level);
}
