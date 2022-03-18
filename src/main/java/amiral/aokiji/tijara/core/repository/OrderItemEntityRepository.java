package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.OrderItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderItemEntityRepository extends CrudRepository<OrderItemEntity, UUID> {
}
