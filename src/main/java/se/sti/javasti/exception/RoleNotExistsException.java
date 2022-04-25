package se.sti.javasti.exception;

public class RoleNotExistsException extends RuntimeException {
    public RoleNotExistsException(String roleName) {
        super(String.format("Role %s does not exists", roleName));
    }
}
