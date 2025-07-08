package microservices.apigateway.controller;

import microservices.apigateway.client.MenuClient;
import model.DishDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/menu")
public class MenuCOntroller {
    private final MenuClient menuClient;

    public MenuCOntroller(MenuClient menuClient) {
        this.menuClient = menuClient;
    }
    @GetMapping("/dish/{id}")
    public DishDTO getDish() {
        System.out.println("KOKOKOKOK");
        try {
            String productId = "123";
            var menu = menuClient.getDish(1L);
            return menu;
        }
//        catch (FeignException.NotFound ex) {
//            throw new OrderNotFoundException("Product not found for order: " + orderId);
//        } catch (FeignException ex) {
//            throw new RuntimeException("Error calling product service: " + ex.getMessage());
//        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
