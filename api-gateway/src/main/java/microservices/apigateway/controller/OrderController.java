package microservices.apigateway.controller;

import feign.FeignException;
import microservices.apigateway.client.MenuClient;
import microservices.apigateway.client.OrderClient;
import microservices.apigateway.client.UserClient;
import microservices.apigateway.exceptions.OrderNotFoundException;
import model.DishDTO;
import model.OrderDTO;
import model.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderClient orderClient;
    private final UserClient userClient;
    private final MenuClient menuClient;

    public OrderController(OrderClient orderClient, UserClient userClient, MenuClient menuClient) {
        this.orderClient = orderClient;
        this.userClient = userClient;
        this.menuClient = menuClient;
    }

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderClient.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        try {
            System.out.println("id " + id);
            OrderDTO orderDTO = orderClient.getOrderById(id);
            return ResponseEntity.ok(orderDTO);
        } catch (FeignException.NotFound ex) {
            System.out.println("id not found" + id);
            Map<String, String> error = new HashMap<>();
            error.put("error", "Не найден заказ " + id);
            error.put("message: ", String.valueOf(id));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
//            return ResponseEntity.notFound().build();
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling ORDER service getOrderById: " + ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO dto) {
        try{
            UserDTO user = userClient.getUserById(dto.getCustomerId());
        } catch (FeignException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Не найден полтзователь " + dto.getCustomerId());
            error.put("message: ", String.valueOf(dto.getCustomerId()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        try {
            double[] totalOrderAmount = {0.0};
            Map<String, String> error = new HashMap<>();
            dto.getItems().forEach(orderItemDTO -> {
                DishDTO dishDto = menuClient.getDish(orderItemDTO.getDishId());
                if (orderItemDTO.getQuantity() <= 0) {
                    error.put("error", "количесвто блюд должно быть > 0 ");
                    error.put("message-" + orderItemDTO.getDishId(), "количесвто блюд должно быть > 0 у id " + orderItemDTO.getDishId());
                    // throw new CountException("количесвто блюд должно быть > 0 у id " + orderItemDTO.getDishId());
                }
                totalOrderAmount[0] += orderItemDTO.getQuantity() * dishDto.getPrice();
            });

            dto.setTotalOrderAmount(BigDecimal.valueOf(totalOrderAmount[0]));
            if (error.get("error") != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
            }
        } catch (FeignException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Не найдено блюдо с id  " + e.getMessage());
            error.put("message: ", String.valueOf(e.getMessage()));
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
        System.out.println(dto);
        try {
            OrderDTO orderDTO = orderClient.createOrder(dto);
            return ResponseEntity.ok(orderDTO);
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling ORDER service createOrder: " + ex.getMessage());
        }
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        try {
           orderClient.deleteOrder(orderId);
            return ResponseEntity.noContent().build();
        } catch (OrderNotFoundException ex) {
            return ResponseEntity.notFound().build();
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling ORDER service deleteOrder: " + ex.getMessage());
        }
    }
}
