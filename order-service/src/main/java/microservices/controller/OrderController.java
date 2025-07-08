package microservices.controller;

import microservices.client.ProductClient;
import microservices.exception.OrderNotFoundException;
import feign.FeignException;
import model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/orders")
public class OrderController {

    private final ProductClient productClient;

    public OrderController(ProductClient productClient) {
        this.productClient = productClient;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {

        if ("999".equals(orderId)) {
           throw new OrderNotFoundException("Order not found with id: " + orderId);
        }

        try {
            String productId = "123";
            var product = productClient.getProduct(productId);
            return ResponseEntity.ok(new Order(orderId, "Order " + orderId, product));
        } catch (FeignException.NotFound ex) {
            throw new OrderNotFoundException("Product not found for order: " + orderId);
        } catch (FeignException ex) {
            throw new RuntimeException("Error calling product service: " + ex.getMessage());
        }

    }
}
