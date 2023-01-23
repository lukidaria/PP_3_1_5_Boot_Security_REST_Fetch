package ru.luki.pp_315_boot_sec_rest_fetch.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.luki.pp_315_boot_sec_rest_fetch.DTO.RoleDTO;
import ru.luki.pp_315_boot_sec_rest_fetch.DTO.UserDTO;
import ru.luki.pp_315_boot_sec_rest_fetch.configs.MapperUtil;
import ru.luki.pp_315_boot_sec_rest_fetch.exceptions.NoSuchUserException;
import ru.luki.pp_315_boot_sec_rest_fetch.model.Role;
import ru.luki.pp_315_boot_sec_rest_fetch.model.User;
import ru.luki.pp_315_boot_sec_rest_fetch.service.UserService;


import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AdminRestController {

    private UserService userService;
    private ModelMapper modelMapper;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    public UserDTO convertToUserDto(User user) {
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userDTO.setRoleDTO(convertToRoleDto(user.getRoles()));
        return userDTO;
    }

    public List<RoleDTO> convertToRoleDto(List<Role> roleList) {
        return roleList.stream()
                .map(r -> modelMapper.map(r, RoleDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/admin")
    public ResponseEntity<List<UserDTO>> showAllUsers(Model model) {
        List<User> userList = userService.allUsers();
        model.addAttribute("user", userList);
        model.addAttribute("allRoles", userService.allRoles());
        return new ResponseEntity<>(MapperUtil.convertList(userList, this::convertToUserDto), HttpStatus.OK);
    }

    @GetMapping("/admin/logout")
    public String logout(HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            request.getSession().invalidate();
        }
        return "logout";
    }


    @GetMapping("/admin/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user with ID "
                    + id + " in DataBase");
        }
        return new ResponseEntity<>(convertToUserDto(user), HttpStatus.OK);
    }

    @PostMapping("/admin")
    public ResponseEntity<Void> saveUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/admin/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        if (user == null) {
            throw new NoSuchUserException("There is no user " +
                    "with ID " + id + " in DataBase.");
        }
        userService.deleteUser(id);
        return new ResponseEntity<>(convertToUserDto(user), HttpStatus.OK);
    }

    @PutMapping(value = "/admin")
    public ResponseEntity<UserDTO> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return new ResponseEntity<>(convertToUserDto(user), HttpStatus.OK);
    }


}
