package ru.luki.pp_315_boot_sec_rest_fetch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.luki.pp_315_boot_sec_rest_fetch.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    User findUserById(Long id);


}
