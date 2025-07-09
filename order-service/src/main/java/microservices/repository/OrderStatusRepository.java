package microservices.repository;

import microservices.entity.OrderStatusEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderStatusRepository extends JpaRepository<OrderStatusEntity, Long> {
    Optional<OrderStatusEntity> findByName(String name);
}
