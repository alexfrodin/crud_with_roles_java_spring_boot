package se.sti.javasti.exeption;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(String username) {
        super(String.format("Username %s already exists", username));
    }
}
