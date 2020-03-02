package com.shedid.api.User.Repository;

import java.util.List;
import com.shedid.api.User.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long>
{
    User findById(long id);

    User findByEmail(String email);

    User findByUsername(String username);

    List<User> findAll();
}