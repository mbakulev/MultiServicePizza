package microservices.client;

import microservices.config.FeignConfig;

import model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "product-service",
        url = "${product.service.url}",
        configuration = FeignConfig.class
)
public interface ProductClient {

    @GetMapping("/products/{id}")
    Product getProduct(@PathVariable String id);
}