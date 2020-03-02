package com.shedid.api.User.Service;

import com.shedid.api.User.Model.NormalUser;

import java.util.List;

/**
 * NormalUserService
 */
public interface NormalUserService
{
    NormalUser save(NormalUser user);

    List<NormalUser> findAll();

    void delete(long id);
}