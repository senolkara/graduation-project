package com.logobootcamp.accountservice.Dao.LoggedUser;

import com.logobootcamp.accountservice.Entities.LoggedUser.LoggedUser;
import com.logobootcamp.accountservice.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;

public interface ILoggedUserRepository extends JpaRepository<LoggedUser, Long> {
    Long countAllByUserAndDate(User user, Date date);
}
