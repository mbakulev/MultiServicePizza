package microservices.menuservice.repository;

import microservices.menuservice.entity.KitchenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KitchenRepository extends JpaRepository<KitchenEntity, Long> {
}
