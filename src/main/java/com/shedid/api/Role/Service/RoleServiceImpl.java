package com.shedid.api.Role.Service;

import com.shedid.api.Role.Model.Role;
import com.shedid.api.Role.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service("roleService")
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleServiceImpl (RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> findAllRole() {
        return this.repository.findAll();
    }

    @Override
    public Role findRoleById(long roleId) {
        return this.repository.findById(roleId);
    }

    @Override
    public void createRole(Role role) {
        this.repository.save(role);
    }

    @Override
    public boolean isRoleExist(String role) {
        return this.repository.findByRole(role) != null;
    }

    @Override
    public Role findRoleByRole(String role) {
        return this.repository.findByRole(role);
    }

    @Override
    public Role createRoleByRole(String role) {
        Role newRole = new Role();
        newRole.setRole(role);
        newRole.setEnabled(true);
        newRole.setTrashed(false);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        newRole.setCreatedAt(timestamp);
        newRole.setUpdatedAt(timestamp);
        this.repository.save(newRole);
        return newRole;
    }
}
