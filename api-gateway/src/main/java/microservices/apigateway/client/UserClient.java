package microservices.apigateway.client;

import model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "user-service",
        url = "${user.service.url}"
)
public interface UserClient {
    @GetMapping("/api/user/{id}")
    UserDTO getUserById(@PathVariable Long id);

    @PostMapping("/api/user")
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @PutMapping("/api/user/{id}")
    UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO);

    @DeleteMapping("/api/user/{id}")
    void deleteUser(@PathVariable Long id);
}
