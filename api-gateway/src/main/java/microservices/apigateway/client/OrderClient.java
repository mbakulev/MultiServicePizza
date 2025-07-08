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

    @GetMapping("/{id}")
    OrderDTO getOrderById(@PathVariable Long id);

    @PostMapping
    OrderDTO createOrder(@RequestBody OrderDTO dto);

    @PutMapping("/{id}")
    OrderDTO updateOrder(@PathVariable Long id, @RequestBody OrderDTO dto);

    @DeleteMapping("/{id}")
    Void deleteOrder(@PathVariable Long id);
}
