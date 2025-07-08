package microservices.apigateway.controller;

import feign.FeignException;
import microservices.apigateway.client.UserClient;
import microservices.apigateway.exceptions.*;
import model.KitchenDTO;
import model.UserDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserClient userClient;

    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        try {
            var user = userClient.getUserById(userId);
            return ResponseEntity.ok(user);
        } catch (FeignException.NotFound ex) {
            return ResponseEntity.notFound().build();
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling USER service getUser: " + ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            var user = userClient.createUser(userDTO);
            return ResponseEntity.ok(user);
        } catch (FeignException.NotFound ex) {
            throw new UserAlreadyExistsException("User already exists with name: " + userDTO.getName());
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling USER service createUser: " + ex.getMessage());
        }
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        try {
            var user = userClient.updateUser(userId, userDTO);
            return ResponseEntity.ok(user);
        } catch (FeignException.NotFound ex) {
            throw new KitchenNotFoundException("Kitchen not found: " + userId);
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling USER service updateUser: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<KitchenDTO> deleteUser(@PathVariable Long userId) {
        try {
            userClient.deleteUser(userId);
            return ResponseEntity.noContent().build();
        } catch (FeignException.NotFound ex) {
            return ResponseEntity.notFound().build();
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling USER service deleteUser: " + ex.getMessage());
        }
    }
}
