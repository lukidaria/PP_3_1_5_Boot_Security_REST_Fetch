package ru.luki.pp_315_boot_sec_rest_fetch.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserDTO {

    private Long id;

    private String firstName;

    private String lastName;

    private Integer age;

    private String username;

    private String password;

    @JsonProperty("roles")
    private List<RoleDTO> roleDTO;

//    private String rolesAtString = roleDTO.stream().map(Object::toString)
//            .map(s->s.substring(5))
//            .collect(Collectors.joining());

    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, Integer age, String username, String password, List<RoleDTO> roleDTO) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
        this.roleDTO = roleDTO;
    }

    public UserDTO(Long id, String firstName, String lastName, Integer age, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDTO> getRoleDTO() {
        return roleDTO;
    }

    public void setRoleDTO(List<RoleDTO> roleDTO) {
        this.roleDTO = roleDTO;
    }
}
