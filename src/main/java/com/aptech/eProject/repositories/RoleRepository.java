package com.aptech.eProject.repositories;

import com.aptech.eProject.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


@Component
public interface RoleRepository extends JpaRepository<Role, Integer>{
    public Role findRoleByName(String name);
}

