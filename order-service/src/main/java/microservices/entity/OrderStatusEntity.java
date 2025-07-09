package microservices.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_status", schema = "order_service")
public class OrderStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_service.order_status_id_seq")
    @SequenceGenerator(name = "order_service.order_status_id_seq", sequenceName = "order_service.order_status_id_seq", allocationSize = 1)
    private Long id;
    private String name;
}
