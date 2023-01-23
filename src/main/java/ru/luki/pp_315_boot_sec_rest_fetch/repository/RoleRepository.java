package ru.luki.pp_315_boot_sec_rest_fetch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.luki.pp_315_boot_sec_rest_fetch.model.Role;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findRoleById(Long id);
}
