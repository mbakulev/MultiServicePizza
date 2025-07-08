package model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;
    private Long customerId;
    private Long statusId;
    private LocalDateTime createdAt;
    private BigDecimal totalOrderAmount;
    private List<OrderItemDTO> items;
}
