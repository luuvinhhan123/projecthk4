package com.aptech.eProject.services;

import java.util.List;

import com.aptech.eProject.models.Role;
import com.aptech.eProject.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public Role findRoleByName(String name) {
        return roleRepository.findRoleByName(name);
    }

    public List<Role> getAll() {
        return roleRepository.findAll();
    }
}