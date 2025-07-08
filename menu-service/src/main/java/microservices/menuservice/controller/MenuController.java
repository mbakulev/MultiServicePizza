package microservices.menuservice.controller;

import microservices.menuservice.exceptions.DishNotFoundException;
import microservices.menuservice.service.MenuService;
import model.DishDTO;
import model.KitchenDTO;
import model.MenuDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping
    public MenuDTO getMenu() {
        return menuService.getMenu();
    }

    @GetMapping("/kitchen/{id}")
    public KitchenDTO getKitchen(@PathVariable Long id) {
        return menuService.getKitchen(id);
    }

    @GetMapping("/dish/{id}")
    public ResponseEntity<?> getDish(@PathVariable Long id) {
        try {
            DishDTO dish = menuService.getDish(id);
            return ResponseEntity.ok(dish);
        } catch (DishNotFoundException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Dish Not Found");
            error.put("message: ", String.valueOf(id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
