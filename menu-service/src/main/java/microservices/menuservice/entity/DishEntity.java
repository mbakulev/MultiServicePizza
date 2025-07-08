package microservices.menuservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dish", schema = "menu_service")
public class DishEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_service.dish_id_seq")
    @SequenceGenerator(name = "menu_service.dish_id_seq", sequenceName = "menu_service.dish_id_seq", allocationSize = 1)
    private Long id;
    private String name;
//    @ManyToOne
//    @JoinColumn(name = "kitchen_id")
//    private KitchenEntity kitchen;
    private Long kitchenId;
    private double price;
}
