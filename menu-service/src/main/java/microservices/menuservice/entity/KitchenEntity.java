package microservices.menuservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kitchen", schema = "menu_service")
public class KitchenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_service.kitchen_id_seq")
    @SequenceGenerator(name = "menu_service.kitchen_id_seq", sequenceName = "menu_service.kitchen_id_seq", allocationSize = 1)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "kitchenId", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Collection<DishEntity> dishes;
}
