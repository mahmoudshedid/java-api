package com.shedid.api.User.Service;

import java.util.List;
import com.shedid.api.User.Model.User;

/**
 * UserService
 */
public interface UserService
{
    User findUserById(long id);

    User findUserByEmail(String email);

    List<User> findAll();

    User createUser(User user);
}