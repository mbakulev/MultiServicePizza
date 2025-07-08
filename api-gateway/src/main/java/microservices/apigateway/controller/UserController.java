package microservices.apigateway.controller;

import feign.FeignException;
import microservices.apigateway.client.UserClient;
import microservices.apigateway.exceptions.*;
import model.KitchenDTO;
import model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserClient userClient;

    public UserController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        try {
            var user = userClient.getUserById(userId);
            System.out.println(user);
            return ResponseEntity.ok(user);
        } catch (FeignException.NotFound ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "User Not Found");
            error.put("message: ", String.valueOf(userId));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling USER service getUser: " + ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        try {
            var user = userClient.createUser(userDTO);
            return ResponseEntity.ok(user);
        } catch (FeignException.Conflict ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "User alreaDY EXISTS EMAIL: " + userDTO.getEmail());
            error.put("message: ", userDTO.getEmail());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
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
