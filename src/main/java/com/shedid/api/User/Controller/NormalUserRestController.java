package com.shedid.api.User.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.shedid.api.User.Model.NormalUser;
import com.shedid.api.User.Service.NormalUserService;

/**
 * NormalUserRestController
 */
@RestController
@RequestMapping("/normal-users")
public class NormalUserRestController
{
    @Autowired
    private NormalUserService userService;

    @RequestMapping(value = "/normal-user", method = RequestMethod.GET)
    public List<NormalUser> listUser()
    {
        return userService.findAll();
    }

    @RequestMapping(value = "/normal-user", method = RequestMethod.POST)
    public NormalUser create(@RequestBody NormalUser user)
    {
        return userService.save(user);
    }

    @RequestMapping(value = "/normal-user/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable(value = "id") Long id)
    {
        userService.delete(id);
        return "success";
    }
}