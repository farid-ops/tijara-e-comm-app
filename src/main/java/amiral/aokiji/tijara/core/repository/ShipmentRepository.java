package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.ShipmentEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ShipmentRepository extends CrudRepository<ShipmentEntity, UUID> {
}
