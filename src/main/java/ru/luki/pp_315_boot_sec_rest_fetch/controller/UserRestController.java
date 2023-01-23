package ru.luki.pp_315_boot_sec_rest_fetch.controller;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.luki.pp_315_boot_sec_rest_fetch.DTO.RoleDTO;
import ru.luki.pp_315_boot_sec_rest_fetch.DTO.UserDTO;
import ru.luki.pp_315_boot_sec_rest_fetch.model.Role;
import ru.luki.pp_315_boot_sec_rest_fetch.model.User;
import ru.luki.pp_315_boot_sec_rest_fetch.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserRestController {

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

    @GetMapping(value = "/user")
    public ResponseEntity<UserDTO> getUser(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        UserDTO userDTO = convertToUserDto(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

}
