package se.sti.javasti.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.sti.javasti.controller.response.OkResponseBody;
import se.sti.javasti.dto.UserResponseDTO;
import se.sti.javasti.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserController(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @GetMapping()
    public OkResponseBody<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> userResponseDTOList = userRepository.findAll().stream()
                .map(user -> mapper.map(user, UserResponseDTO.class)).collect(Collectors.toList());
        return new OkResponseBody<>("All users", userResponseDTOList);
    }

}
