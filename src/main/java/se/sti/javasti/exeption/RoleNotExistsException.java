package se.sti.javasti.exeption;

public class RoleNotExistsException extends RuntimeException {
    public RoleNotExistsException(String roleName) {
        super(String.format("Role %s does not exists", roleName));
    }
}
