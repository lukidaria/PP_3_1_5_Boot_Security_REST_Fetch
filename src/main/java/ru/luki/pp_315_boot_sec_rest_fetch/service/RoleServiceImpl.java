package ru.luki.pp_315_boot_sec_rest_fetch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.luki.pp_315_boot_sec_rest_fetch.model.Role;
import ru.luki.pp_315_boot_sec_rest_fetch.repository.RoleRepository;

import java.util.List;

@Service
public class RoleServiceImpl {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    public List<Role> getRolesById(Long id){
        return roleRepository.findRoleById(id);
    }
}
