package com.shedid.api.User.Service;

import java.util.List;

import com.shedid.api.Gender.Model.Gender;
import com.shedid.api.Role.Model.Role;
import com.shedid.api.User.Model.User;

/**
 * UserService
 */
public interface UserService
{
    User findUserById(long id);

    User findUserByEmail(String email);

    User findUserByUsername(String username);

    Boolean exitsByUsername(String username);

    List<User> findAll();

    User createUser(User user);

    User createUserByUsername(String username, Role role, Gender gender);
}