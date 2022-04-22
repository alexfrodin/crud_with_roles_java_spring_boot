package se.sti.javasti.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se.sti.javasti.exeption.UserAlreadyExistsException;
import se.sti.javasti.model.Role;
import se.sti.javasti.model.User;
import se.sti.javasti.repository.UserRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
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
        if(userOptional.isPresent()) throw new UserAlreadyExistsException(userOptional.get().getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User userToUpdate = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
        updatedUser.setId(userToUpdate.getId());
        if(updatedUser.getUsername() == null) updatedUser.setUsername(userToUpdate.getUsername());
        if (updatedUser.getPassword() == null) {
            updatedUser.setPassword(userToUpdate.getPassword());
        } else {
            updatedUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
        }
        if(updatedUser.getIsEnabled() == null) updatedUser.setIsEnabled(userToUpdate.getIsEnabled());
        if(updatedUser.getRoles() == null) updatedUser.setRoles(userToUpdate.getRoles());
        return userRepository.save(updatedUser);
    }
}
