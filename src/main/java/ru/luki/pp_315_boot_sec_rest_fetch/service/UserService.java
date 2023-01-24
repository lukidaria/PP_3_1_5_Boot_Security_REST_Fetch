package ru.luki.pp_315_boot_sec_rest_fetch.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.luki.pp_315_boot_sec_rest_fetch.model.Role;
import ru.luki.pp_315_boot_sec_rest_fetch.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {

    User findUserById(Long userId);

    User findByUsername(String username);

    List<User> allUsers();

    List<Role> allRoles();

    boolean saveUser(User user);

    void deleteUser(Long userId);

    void updateUser(User user);






}
