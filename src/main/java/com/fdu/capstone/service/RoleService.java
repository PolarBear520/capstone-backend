package com.fdu.capstone.service;

import com.fdu.capstone.model.Role;
import com.fdu.capstone.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleById(Long id) {
        Optional<Role> roleOptional = roleRepository.findById(id);
        return roleOptional.orElse(null);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
