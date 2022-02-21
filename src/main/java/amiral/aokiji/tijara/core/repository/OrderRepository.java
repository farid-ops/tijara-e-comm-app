package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.OrderEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface OrderRepository extends CrudRepository<OrderEntity, UUID> {
}
