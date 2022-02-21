package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.ItemEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ItemRepository extends CrudRepository<ItemEntity, UUID> {
}
