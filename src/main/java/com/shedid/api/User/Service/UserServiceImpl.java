package com.shedid.api.User.Service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import com.shedid.api.User.Model.User;
import com.shedid.api.User.Repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 */
@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService
{

    private final UserRepository repository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException
    {
        User user = repository.findByUsername(userId);
        if (user == null) { throw new UsernameNotFoundException("Invalid username or password."); }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority()
    {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
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