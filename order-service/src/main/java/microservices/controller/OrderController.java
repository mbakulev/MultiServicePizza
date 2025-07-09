package microservices.controller;

//import microservices.client.ProductClient;
//import microservices.exception.OrderNotFoundException;
//import feign.FeignException;
//import model.Order;
//import org.springframework.http.ResponseEntity;
import microservices.service.OrderService;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public OrderDTO getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDTO orderDTO) {

        try {
            OrderDTO createdOrder = orderService.createOrder(orderDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
        } catch (Exception ex) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Проблема с созданием заказаl " + orderDTO);
            error.put("message: ", String.valueOf(orderDTO));
            return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
        }
    }

//    private final ProductClient productClient;
//
//    public OrderController(ProductClient productClient) {
//        this.productClient = productClient;
//    }
//
//    @GetMapping("/{orderId}")
//    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
//
//        if ("999".equals(orderId)) {
//           throw new OrderNotFoundException("Order not found with id: " + orderId);
//        }
//
//        try {
//            String productId = "123";
//            var product = productClient.getProduct(productId);
//            return ResponseEntity.ok(new Order(orderId, "Order " + orderId, product));
//        } catch (FeignException.NotFound ex) {
//            throw new OrderNotFoundException("Product not found for order: " + orderId);
//        } catch (FeignException ex) {
//            throw new RuntimeException("Error calling product service: " + ex.getMessage());
//        }
//
//    }
}
