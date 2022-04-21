package se.sti.javasti.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import se.sti.javasti.controller.response.OkResponseBody;
import se.sti.javasti.dto.UserRequestDTO;
import se.sti.javasti.dto.UserResponseDTO;
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
    public void deleteUser(@PathVariable(name = "userId") String userId) {
        System.out.println(userId);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('ADMIN')")
    public OkResponseBody<UserResponseDTO> updateUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        User userToCreate = mapper.map(userRequestDTO, User.class);
        return new OkResponseBody<>("Created user", mapper.map(
                userService.createUser(userToCreate), UserResponseDTO.class
        ));
    }

}
