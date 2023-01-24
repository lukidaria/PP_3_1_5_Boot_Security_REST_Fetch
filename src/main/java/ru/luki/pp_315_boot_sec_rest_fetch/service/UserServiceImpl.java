package ru.luki.pp_315_boot_sec_rest_fetch.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.luki.pp_315_boot_sec_rest_fetch.model.Role;
import ru.luki.pp_315_boot_sec_rest_fetch.model.User;
import ru.luki.pp_315_boot_sec_rest_fetch.repository.RoleRepository;
import ru.luki.pp_315_boot_sec_rest_fetch.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private RoleRepository roleRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setbCryptPasswordEncoder(@Lazy BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " not found");
        }

        return user;

//                new org.springframework.security.core.userdetails.User(user.getUsername()
//                , user.getPassword(), mapRolesAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());

    }

    public User findUserById(Long userId) {
        User userFromDb = userRepository.findUserById(userId);
        return userFromDb;
    }

    public User findByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    @Transactional
    public List<User> allUsers() {
        return userRepository.findAll();
    }



    @Transactional
    public List<Role> allRoles() {
        return roleRepository.findAll();
    }

    @Transactional
    public boolean saveUser(User user) {
        User userFromDB = findUserById(user.getId());

        if (userFromDB != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Transactional
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(User user) {
        User entity = userRepository.findUserById(user.getId());
        entity.setId(user.getId());
        userRepository.save(user);
    }

}
