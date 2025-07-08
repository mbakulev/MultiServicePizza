package microservices.apigateway.client;

import model.DishDTO;
import model.KitchenDTO;
import model.MenuDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(
        name = "menu-service",
        url = "${menu.service.url}"
)
public interface MenuClient {
        @GetMapping("/api/menu/dish/{id}")
        DishDTO getDish(@PathVariable Long id);

        @GetMapping("/api/menu/kitchen/{id}")
        KitchenDTO getKitchen(@PathVariable Long id);

        @GetMapping("/api/menu")
        MenuDTO getMenu();
}

