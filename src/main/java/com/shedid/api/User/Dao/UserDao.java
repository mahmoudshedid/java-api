package com.shedid.api.User.Dao;

import com.shedid.api.User.Model.NormalUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * UserDao
 */
@Repository
public interface UserDao extends CrudRepository<NormalUser, Long>
{
    NormalUser findByUsername(String username);
}