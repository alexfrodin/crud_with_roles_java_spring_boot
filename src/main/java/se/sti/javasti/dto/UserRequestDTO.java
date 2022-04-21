package se.sti.javasti.dto;

import org.springframework.validation.annotation.Validated;
import se.sti.javasti.model.Role;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Validated
public class UserRequestDTO {

    @NotNull(message = "Username may not be null")
    @NotBlank(message = "Username is required")
    private String username;

    @NotNull(message = "Password may not be null")
    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "At least one role is required")
    private Set<Role> roles;

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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
