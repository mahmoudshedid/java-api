package com.shedid.api.User.Service;

import java.sql.Timestamp;
import java.util.List;

import com.shedid.api.User.Model.User;
import com.shedid.api.User.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 */
@Service("userService")
public class UserServiceImpl implements UserService
{

    private final UserRepository repository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public User findUserById(long id)
    {
        return repository.findById(id);
    }

    public User findUserByEmail(String email)
    {
        return repository.findByEmail(email);
    }

    public List<User> findAll()
    {
        return repository.findAll();
    }

    public User createUser(User user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setTrashed(false);
        user.setEnabled(true);

        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user.setCreatedAt(timestamp);
        user.setUpdatedAt(timestamp);
        repository.save(user);
        return user;
    }
}