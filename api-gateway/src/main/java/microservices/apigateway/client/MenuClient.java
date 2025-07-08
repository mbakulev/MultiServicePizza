package microservices.apigateway.client;

import model.DishDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(
        name = "user-service",
        url = "${user.service.url}"
)
public interface MenuClient {
        @GetMapping("/api/menu/dish/{id}")
        DishDTO getDish(@PathVariable Long id);
}

