package com.shedid.api.User.Controller;

import javax.validation.Valid;

import com.shedid.api.User.Model.User;
import com.shedid.api.User.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * UserRestController
 */
@RestController
public class UserRestController
{

    private final UserService service;

    @Autowired
    public UserRestController(UserService service)
    {
        this.service = service;
    }

    @RequestMapping(value = "user/get/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable("id") long id)
    {
        return service.findUserById(id);
    }

    @RequestMapping(value = "user/new", method = RequestMethod.POST)
    public User postCreateUser(@Valid @RequestBody User user)
    {
        this.service.createUser(user);
        return user;
    }

}