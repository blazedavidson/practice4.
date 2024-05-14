package ru.practice4.beans;

import ru.practice4.model.SQLUsers;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jdbc.repository.query.Query;
import java.util.Set;

@Repository
public interface SelectUsers extends CrudRepository<SQLUsers, Long> {

    @Query("SELECT * FROM my_postgres.users s WHERE s.username like :username")
    Set<SQLUsers> findMyUsersByUsername(@Param("username") String username);
    SQLUsers findFirstByUsername(String username);
    long countByUsername( String userName);
}
