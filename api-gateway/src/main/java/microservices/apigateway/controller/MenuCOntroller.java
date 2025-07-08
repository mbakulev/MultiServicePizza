package microservices.apigateway.controller;

import feign.FeignException;
import microservices.apigateway.client.MenuClient;
import microservices.apigateway.exceptions.DishNotFoundException;
import microservices.apigateway.exceptions.KitchenNotFoundException;
import microservices.apigateway.exceptions.MenuNotFoundException;
import model.DishDTO;
import model.KitchenDTO;
import model.MenuDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class MenuCOntroller {
    private final MenuClient menuClient;

    public MenuCOntroller(MenuClient menuClient) {
        this.menuClient = menuClient;
    }
    @GetMapping("/dish/{dishId}")
    public ResponseEntity<DishDTO> getDish(@PathVariable Long dishId) {
        try {
            var dish = menuClient.getDish(dishId);
            return ResponseEntity.ok(dish);
        } catch (FeignException.NotFound ex) {
            throw new DishNotFoundException("Dish not found: " + dishId);
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling MENU service getDish: " + ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<MenuDTO> getMenu() {
        try {
            var menu = menuClient.getMenu();
            return ResponseEntity.ok(menu);
        } catch (FeignException.NotFound ex) {
            throw new MenuNotFoundException("Menu not found");
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling MENU service getMenu: " + ex.getMessage());
        }
    }

    @GetMapping("/kitchen/{kitchenId}")
    public ResponseEntity<KitchenDTO> getKitchen(@PathVariable Long kitchenId) {
        try {
            var kitchen = menuClient.getKitchen(kitchenId);
            return ResponseEntity.ok(kitchen);
        } catch (FeignException.NotFound ex) {
            throw new KitchenNotFoundException("Kitchen not found: " + kitchenId);
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling MENU service getKitchen: " + ex.getMessage());
        }
    }
}
