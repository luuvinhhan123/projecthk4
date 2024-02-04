package com.project.ecommerce.services;

import com.project.ecommerce.entities.ERole;
import com.project.ecommerce.entities.Role;
import com.project.ecommerce.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService  {
    @Autowired
    RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}
