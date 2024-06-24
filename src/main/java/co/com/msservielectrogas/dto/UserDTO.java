package co.com.msservielectrogas.dto;

import co.com.msservielectrogas.enums.ERoles;

public class UserDTO {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private ERoles roleName;
    private Integer role;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String email, ERoles roleName, Integer role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.roleName = roleName;
        this.role = role;
    }
    
    public UserDTO(Integer id, String name, String email, String password, ERoles roleName, Integer role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roleName = roleName;
        this.role = role;
    }

    // Getters
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public ERoles getRoleName() {
        return roleName;
    }

    public Integer getRole() {
        return role;
    }

    // Setters
    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setTypeName(ERoles roleName) {
        this.roleName = roleName;
    }

    public void setType(Integer role) {
        this.role = role;
    }
}
