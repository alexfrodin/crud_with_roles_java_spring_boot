package se.sti.javasti.services;

import se.sti.javasti.model.Role;
import se.sti.javasti.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAllUsers();

    Set<Role> getMyRoles(String username);

    User createUser(User user);

    void deleteUser(Long userId);

    User updateUser(Long id, User user);

}
