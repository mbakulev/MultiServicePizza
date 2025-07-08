package microservices.controller;

import microservices.exception.ProductNotFoundException;
import model.Product;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable String id) {
        // В реальном приложении здесь был бы вызов репозитория
        if ("999".equals(id)) {
            throw new ProductNotFoundException("Product not found with id: " + id);
        }
        return new Product(id, "Product " + id, 100.0);
    }
}
