package com.project.barista101.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.barista101.data.ERoles;
import com.project.barista101.model.account.Roles;
import com.project.barista101.repository.RoleRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RoleService {
    
    @Autowired
    private final RoleRepository roleRepository;

    @Transactional
    public Roles getRole(ERoles roleName){
        return roleRepository.findByName(roleName)
            .orElseThrow(() -> new IllegalStateException("Role with current name cannot be found: "+roleName));
    }
}
