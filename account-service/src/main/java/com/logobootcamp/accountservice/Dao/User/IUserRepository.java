package com.logobootcamp.accountservice.Dao.User;

import com.logobootcamp.accountservice.Entities.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);
    User findTopByOrderByIdDesc();
}
