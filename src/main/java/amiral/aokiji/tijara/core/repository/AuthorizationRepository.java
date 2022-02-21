package amiral.aokiji.tijara.core.repository;

import amiral.aokiji.tijara.core.model.AuthorizationEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface AuthorizationRepository extends CrudRepository<AuthorizationEntity, UUID> {
}
