package com.logobootcamp.adminuserservice.Dao.User;

import com.logobootcamp.adminuserservice.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
