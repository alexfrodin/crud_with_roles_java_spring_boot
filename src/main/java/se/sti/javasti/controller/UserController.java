package se.sti.javasti.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.sti.javasti.controller.response.OkResponseBody;
import se.sti.javasti.dto.UserCreateRequestDTO;
import se.sti.javasti.dto.UserResponseDTO;
import se.sti.javasti.dto.UserUpdateRequestDTO;
import se.sti.javasti.model.Role;
import se.sti.javasti.model.User;
import se.sti.javasti.services.UserService;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
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

    @GetMapping(value = "/my-roles",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public OkResponseBody<Set<Role>> getMyRoles(Principal principal) {
        return new OkResponseBody<>("Your roles", userService.getMyRoles(principal.getName()));
    }

    @GetMapping(
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public OkResponseBody<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOList = userService.getAllUsers().stream()
                .map(user -> mapper.map(user, UserResponseDTO.class))
                .collect(Collectors.toList());
        return new OkResponseBody<>("All users", userResponseDTOList);
    }

    @DeleteMapping(value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    public OkResponseBody<String> deleteUser(@PathVariable(name = "userId") Long userId) {
        userService.deleteUser(userId);
        return new OkResponseBody<>(String.format("User with id %s successfully deleted", userId), null);
    }

    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public OkResponseBody<UserResponseDTO> createUser(@RequestBody @Valid UserCreateRequestDTO userCreateRequestDTO) {
        User userToCreate = mapper.map(userCreateRequestDTO, User.class);
        return new OkResponseBody<>("Created user", mapper.map(
                userService.createUser(userToCreate), UserResponseDTO.class
        ));
    }

    @PutMapping(value = "/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    @PreAuthorize("hasAnyAuthority('ADMIN', 'EDITOR')")
    public OkResponseBody<UserResponseDTO> updateUser(@PathVariable(name = "userId") Long id, @RequestBody @Valid @NotEmpty UserUpdateRequestDTO userUpdateRequestDTO) {
        User userToUpdate = mapper.map(userUpdateRequestDTO, User.class);
        if (userUpdateRequestDTO.getRoles() != null) userUpdateRequestDTO.getRoles().forEach(userToUpdate::addRole);
        return new OkResponseBody<>("Successfully updated user", mapper.map(
                userService.updateUser(id, userToUpdate), UserResponseDTO.class
        ));
    }

}
