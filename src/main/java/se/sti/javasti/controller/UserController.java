package se.sti.javasti.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.sti.javasti.controller.response.OkResponseBody;
import se.sti.javasti.dto.UserCreateRequestDTO;
import se.sti.javasti.dto.UserResponseDTO;
import se.sti.javasti.dto.UserUpdateRequestDTO;
import se.sti.javasti.model.Role;
import se.sti.javasti.model.User;
import se.sti.javasti.services.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserService userService, ModelMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @GetMapping("/my-roles")
    public OkResponseBody<Set<Role>> getMyRoles(Principal principal) {
        return new OkResponseBody<>("Your roles", userService.getMyRoles(principal.getName()));
    }

    @GetMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public OkResponseBody<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOList = userService.getAllUsers().stream()
                .map(user -> mapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
        return new OkResponseBody<>("All users", userResponseDTOList);
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public OkResponseBody<String> deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return new OkResponseBody<>(String.format("User with id %s successfully deleted", userId), null);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public OkResponseBody<UserResponseDTO> createUser(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        User userToCreate = mapper.map(userCreateRequestDTO, User.class);
        return new OkResponseBody<>("Created user", mapper.map(
                userService.createUser(userToCreate), UserResponseDTO.class
        ));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    public OkResponseBody<UserResponseDTO> updateUser(@PathVariable(name = "userId") Long id, @RequestBody @Valid UserUpdateRequestDTO userUpdateRequestDTO) {
        User userToUpdate = mapper.map(userUpdateRequestDTO, User.class);
        return new OkResponseBody<>("Successfully updated user", mapper.map(
                userService.updateUser(id, userToUpdate), UserResponseDTO.class
        ));
    }

}
