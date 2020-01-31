package cft.focusstart.finalprogect.authservice.repository;

import cft.focusstart.finalprogect.authservice.model.SystemUser;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SystemUserRepository extends CrudRepository<SystemUser, Long> {

    @Modifying(clearAutomatically=true)
    @Query("UPDATE SystemUser SET name=:name, password=:password WHERE login=:login")
    SystemUser updateSystemUserByLogin(@Param("login")String login, @Param("name")String name, @Param("password")String password);

    @Modifying
    @Query("delete from SystemUser WHERE login = ?1 AND NOT(role='ADMIN' AND ((select COUNT(*) from SystemUser)=1))")
    void deleteByLogin(String login);
}

