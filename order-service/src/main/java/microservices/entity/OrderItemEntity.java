package microservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_items", schema = "order_service")
public class OrderItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_service.order_items_id_seq")
    @SequenceGenerator(name = "order_service.order_items_id_seq", sequenceName = "order_service.order_items_id_seq", allocationSize = 1)
    private Long id;
//    @ManyToOne
//    @JoinColumn(name = "order_id")
//    private OrderEntity order;
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "order_id", nullable = false)  // Foreign key column
//    private OrderEntity order;
//    private Long orderId;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)  // Foreign key column
    private OrderEntity order;  // JPA entity reference for the order
//    @ManyToOne
//    @JoinColumn(name = "dish_id")
//    private DishEntity dish;
    private Long dishId;
    private Long quantity;
}
