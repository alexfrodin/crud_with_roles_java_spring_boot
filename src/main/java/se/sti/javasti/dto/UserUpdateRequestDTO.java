package se.sti.javasti.dto;

import se.sti.javasti.model.Role;

import java.util.HashSet;
import java.util.Set;

public class UserUpdateRequestDTO {

    private String username;
    private String password;
    private Boolean isEnabled;
    private Set<Role> roles = new HashSet<>();

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

    public Boolean getIsEnabled() {
        return this.isEnabled;
    }

    public void setIsEnabled(Boolean enabled) {
        this.isEnabled = enabled;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = new HashSet<>(roles);
    }
}
