package com.logobootcamp.otheruserservice.Dao.User;

import com.logobootcamp.otheruserservice.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
