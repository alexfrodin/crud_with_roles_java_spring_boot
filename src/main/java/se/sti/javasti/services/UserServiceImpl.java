package se.sti.javasti.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.sti.javasti.exception.RoleNotExistsException;
import se.sti.javasti.exception.UserAlreadyExistsException;
import se.sti.javasti.model.Role;
import se.sti.javasti.model.User;
import se.sti.javasti.repository.RoleRepository;
import se.sti.javasti.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Set<Role> getMyRoles(String username) {
        User userResponse = userRepository.getUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User name %s not found", username)));
        return userResponse.getRoles();
    }

    @Override
    public User createUser(User user) {
        Optional<User> userOptional = userRepository.getUserByUsername(user.getUsername());
        if (userOptional.isPresent()) throw new UserAlreadyExistsException(userOptional.get().getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    @Transactional
    public User updateUser(Long id, User updatedUser) {
        User userToUpdate = userRepository.findById(id)
                .map((user) -> {
                            if (updatedUser.getUsername() != null)
                                user.setUsername(updatedUser.getUsername());
                            if (updatedUser.getPassword() != null)
                                user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                            if (updatedUser.getIsEnabled() != null)
                                user.setIsEnabled(updatedUser.getIsEnabled());
                            if (!updatedUser.getRoles().isEmpty()) {
                                Set<Role> newRoles = updatedUser.getRoles().stream().map(role -> {
                                    Role foundRole = roleRepository.findByName(role.getName())
                                            .orElseThrow(() -> new RoleNotExistsException(role.getName()));
                                    return foundRole;
                                }).collect(Collectors.toSet());
                                user.setRoles(newRoles);
                            }
                            return user;
                        }
                )
                .orElseThrow(() -> new EntityNotFoundException("User not found"));
        return userRepository.save(userToUpdate);
    }
}
