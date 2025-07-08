package model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OrderItemDTO {
    private Long id;
    private Long dishId;
    private Long quantity;
    private BigDecimal price;
}