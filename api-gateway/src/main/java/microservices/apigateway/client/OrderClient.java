package microservices.apigateway.client;

import model.OrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "order-service",
        url = "${order.service.url}"
)
public interface OrderClient {
    @GetMapping
    List<OrderDTO> getAllOrders();

    @GetMapping("/api/order/{id}")
    OrderDTO getOrderById(@PathVariable Long id);

    @PostMapping("/api/order")
    OrderDTO createOrder(@RequestBody OrderDTO dto);

    @PutMapping("/api/order/{id}")
    OrderDTO updateOrder(@PathVariable Long id, @RequestBody OrderDTO dto);

    @DeleteMapping("/api/order/{id}")
    Void deleteOrder(@PathVariable Long id);
}
