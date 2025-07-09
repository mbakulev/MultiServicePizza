package microservices.userservice.controller;

import microservices.userservice.entity.UserEntity;
import microservices.userservice.exception.UserAlreadyExistsException;
import microservices.userservice.exception.UserNotFoundException;
import microservices.userservice.mapper.UserMapper;
import microservices.userservice.repository.UserRepository;
import microservices.userservice.service.UserService;
import model.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user")
public class UserCOntroller {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {

        try {
            UserDTO userDTO = userService.getUserById(id);
            return ResponseEntity.ok(userDTO);
        } catch (UserNotFoundException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "User not found with id " + id);
            error.put("message: ", String.valueOf(id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {

        try {
            UserDTO createdUser = userService.createUser(userDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (UserAlreadyExistsException ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "User already exists with mail " + userDTO.getEmail());
            error.put("message: ", String.valueOf(userDTO.getEmail()));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
//        Optional<UserEntity> optionalUser = userRepository.findById(id);
//        if (optionalUser.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//
//        UserEntity user = optionalUser.get();
//        user.setName(userDTO.getName());
//        user.setEmail(userDTO.getEmail());
//
//        UserEntity updated = userRepository.save(user);
//        return ResponseEntity.ok(UserMapper.toDto(updated));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        userRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
