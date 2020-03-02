package com.shedid.api.User.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.shedid.api.User.Dao.UserDao;
import com.shedid.api.User.Model.NormalUser;

/**
 * NormalUserServiceImpl
 */
@Service(value = "normalUserService")
public class NormalUserServiceImpl implements UserDetailsService, NormalUserService
{
    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        NormalUser user = userDao.findByUsername(username);
        if (user == null) { throw new UsernameNotFoundException("Invalid username or password."); }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority()
    {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    public List<NormalUser> findAll()
    {
        List<NormalUser> list = new ArrayList<>();
        userDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

    @Override
    public void delete(long id)
    {
        userDao.deleteById(id);
    }

    @Override
    public NormalUser save(NormalUser user)
    {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userDao.save(user);
    }
}