package microservices.apigateway.controller;

import feign.FeignException;
import microservices.apigateway.client.OrderClient;
import microservices.apigateway.exceptions.DishNotFoundException;
import microservices.apigateway.exceptions.OrderNotFoundException;
import microservices.apigateway.exceptions.UserNotFoundException;
import model.OrderDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderClient orderClient;

    public OrderController(OrderClient orderClient) {this.orderClient = orderClient;}

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        return ResponseEntity.ok(orderClient.getAllOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        try {
            OrderDTO orderDTO = orderClient.getOrderById(id);
            return ResponseEntity.ok(orderDTO);
        } catch (FeignException.NotFound ex) {
            return ResponseEntity.notFound().build();
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling ORDER service getOrderById: " + ex.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO dto) {
        try {
            OrderDTO orderDTO = orderClient.createOrder(dto);
            return ResponseEntity.ok(orderDTO);
        } catch (UserNotFoundException | DishNotFoundException ex) {
            return ResponseEntity.notFound().build();
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
