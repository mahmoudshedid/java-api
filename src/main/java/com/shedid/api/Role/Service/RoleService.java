package com.shedid.api.Role.Service;

import com.shedid.api.Role.Model.Role;

import java.util.List;

public interface RoleService {

    void createRole(Role role);

    Role findRoleById(long roleId);

    Role findRoleByRole(String role);

    boolean isRoleExist(String role);

    List<Role> findAllRole();

    Role createRoleByRole(String role);
}
