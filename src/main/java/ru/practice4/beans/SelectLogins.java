package ru.practice4.beans;

import ru.practice4.model.SQLLogins;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jdbc.repository.query.Modifying;

@Repository
public interface SelectLogins extends CrudRepository<SQLLogins, Long> {
    @Modifying
    @Query("delete from POSTGRES.LOGINS lg where lg.USER_ID = :userId")
    void deleteLoginsForUserId(@Param("userId") Long userId);

    long countByUserId( Long userId);
}
