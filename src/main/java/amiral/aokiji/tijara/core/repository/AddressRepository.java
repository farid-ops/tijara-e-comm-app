package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.AddressEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AddressRepository extends CrudRepository<AddressEntity, UUID> {
}
