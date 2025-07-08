package microservices.apigateway.client;

import model.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "user-service",
        url = "${user.service.url}"
)
public interface UserClient {
    @GetMapping("/{id}")
    UserDTO getUserById(@PathVariable Long id);

    @PostMapping
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @PutMapping("/{id}")
    UserDTO updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO);

    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable Long id);
}
