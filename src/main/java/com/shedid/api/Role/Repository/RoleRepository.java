package com.shedid.api.Role.Repository;

import com.shedid.api.Role.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findById(long id);

    Role findByRole(String role);
}
